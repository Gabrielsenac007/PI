package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.AlunoRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.AssDiscAndProfReqDto;
import com.Projeto.pi.medioTec.Dto.Request.UserAuthenticationRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.classes.ClassDto;
import com.Projeto.pi.medioTec.Dto.Response.AlunoDTO;
import com.Projeto.pi.medioTec.Dto.Response.LoginResponseDto;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Entity.User.UserRole;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Repository.ClassesRepository;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import com.Projeto.pi.medioTec.Repository.UsersRepository;
import com.Projeto.pi.medioTec.security.TokenService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private Cloudinary cloudinary;

    public LoginResponseDto login(UserAuthenticationRequestDto data) {

        if (data.cpf() == null || data.cpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não fornecido");
        }

        Users user = usersRepository.findByCpf(data.cpf());
        if (user == null) {
            throw new UsernameNotFoundException("CPF não encontrado");
        }

        var userLogin = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        try {
            var auth = this.authenticationManager.authenticate(userLogin);
            user = (Users) auth.getPrincipal(); // Re-obter usuário autenticado
            String token = tokenService.generateToken(user);

            // Inicialize dados da classe
            String classId = null;
            String className = null;
            String schoolYear = null;
            String shift = null;
            Integer semester = null;

            // Verifique se o usuário é ALUNO e se possui uma classe associada
            if (user.getRole() == UserRole.ALUNO && user.getStudentClass() != null) {
                Classes turma = user.getStudentClass();
                classId = turma.getId();
                className = turma.getNameClass();
                schoolYear = turma.getSchoolYear();
                shift = turma.getShift();
                semester = turma.getSemester();
            }

            // Retorne a resposta achatada
            return new LoginResponseDto(
                    token,
                    user.getName(),
                    user.getRole(),
                    user.getId(),
                    classId,
                    className,
                    schoolYear,
                    shift,
                    semester
            );

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Erro ao realizar login: CPF não encontrado");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Erro ao realizar login: Senha incorreta");
        } catch (Exception e) {
            throw new BadCredentialsException("Erro ao realizar login: " + e.getMessage());
        }
    }



    public Users getByCpf(String cpf){
        Users user = (Users) usersRepository.findByCpf(cpf);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return user;
    }

    public List<AlunoDTO> getAllStudents() {
        List<Users> students = usersRepository.findByRole(UserRole.ALUNO);
        return students.stream()
                .map(user -> new AlunoDTO(user.getId(),user.getCpf(), user.getName(), user.getEmail(), user.getImgProfile() ,user.getStudentClass()))
                .collect(Collectors.toList());
    }


    public List<Users> getAllProfessor(){
        return usersRepository.findByRole(UserRole.PROFESSOR);
    }

    public List<Users> getAllCoordenador(){
        return usersRepository.findByRole(UserRole.COORDENADOR);
    }
    private void createUserWithRole(UserRegisterRequestDto register, UserRole role){
        Users user = (Users) usersRepository.findByCpf(register.cpf());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já existe já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        Users newUser = new Users(register.cpf(), register.name(), register.email(), encryptedPassword, role);
        usersRepository.save(newUser);
    }

    private void createProfessor(UserRegisterRequestDto register, List<AssDiscAndProfReqDto> disciplines, UserRole role){

        Users user = (Users) usersRepository.findByCpf(register.cpf());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já existe já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        Users newUser = new Users(register.cpf(), register.name(), register.email(), encryptedPassword, role);
        Users professor = usersRepository.save(newUser);
        String professorId = professor.getId();

        for(AssDiscAndProfReqDto data : disciplines){
            disciplinesRepository.associate_professor_and_discipline(professorId, data.disciplinaId());
        }

    }

    private void createAluno(AlunoRegisterRequestDto register, UserRole role, MultipartFile imgProfile){

        Optional<Classes> opClasses = classesRepository.findById(register.classeId());
        if (opClasses.isEmpty()){
            throw new IllegalArgumentException("Classe não encontrada");
        }

        Classes classes = opClasses.get();

        Users user = (Users) usersRepository.findByCpf(register.cpf());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já existe já existe");
        }

        String imgProfileUrl = null;
        if (imgProfile != null && !imgProfile.isEmpty()) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imgProfile.getBytes(), ObjectUtils.emptyMap());
                imgProfileUrl = (String) uploadResult.get("secure_url");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao fazer upload da imagem de perfil: " + e.getMessage(), e);
            }
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        Users newUser = new Users(register.cpf(), register.name(), register.email(), encryptedPassword, classes, role, imgProfileUrl);
        usersRepository.save(newUser);

    }

    public void insertProfessor(UserRegisterRequestDto register, List<AssDiscAndProfReqDto> disciplines){
        createProfessor(register, disciplines,UserRole.PROFESSOR);
    }

    public void insertCoordinator(UserRegisterRequestDto register){
        createUserWithRole(register, UserRole.COORDENADOR );
    }

    public void deleteCoordinator(String id){
        usersRepository.deleteById(id);
    }

    public void insertStudent(AlunoRegisterRequestDto register, MultipartFile imgProfile){
        createAluno(register, UserRole.ALUNO, imgProfile);
    }

    public void  deleteProfessor(String id) {
        usersRepository.deleteById(id);
    }

    public void deleteStudent(String id) {
        usersRepository.deleteById(id);
    }

    public Users updateProfessor(String id, UserRegisterRequestDto updatedData) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Users user = optionalUser.get();
        if (updatedData.name() != null && !updatedData.name().isEmpty()) {
            user.setName(updatedData.name());
        }
        if (updatedData.email() != null && !updatedData.email().isEmpty()) {
            user.setEmail(updatedData.email());
        }
        if (updatedData.password() != null && !updatedData.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(updatedData.password());
            user.setPassword(encryptedPassword);
        }

        return usersRepository.save(user);
    }

    public Users updateStudent(String id, UserRegisterRequestDto updatedData) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Users user = optionalUser.get();

        if (updatedData.name() != null && !updatedData.name().isEmpty()) {
            user.setName(updatedData.name());
        }
        if (updatedData.email() != null && !updatedData.email().isEmpty()) {
            user.setEmail(updatedData.email());
        }
        if (updatedData.password() != null && !updatedData.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(updatedData.password());
            user.setPassword(encryptedPassword);
        }

        return usersRepository.save(user);
    }

    public Users updateCoordinator(String id, UserRegisterRequestDto updatedData) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Coordinator não encontrado");
        }
        Users user = optionalUser.get();

        if (updatedData.name() != null && !updatedData.name().isEmpty()) {
            user.setName(updatedData.name());
        }
        if (updatedData.email() != null && !updatedData.email().isEmpty()) {
            user.setEmail(updatedData.email());
        }
        if (updatedData.password() != null && !updatedData.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(updatedData.password());
            user.setPassword(encryptedPassword);
        }

        return usersRepository.save(user);
    }

    public List<Users> getAllStudentsByClassId(String classId) {
        return usersRepository.findByStudentClass_Id(classId);
    }

    public List<Users> getProfessorsByClassId(String classId) {
        Optional<Classes> optionalClass = classesRepository.findById(classId);
        if (optionalClass.isEmpty()) {
            throw new IllegalArgumentException("Classe não encontrada");
        }
        Classes classe = optionalClass.get();
        Set<Disciplines> disciplines = classe.getDisciplines();

        if (disciplines.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma disciplina associada à turma");
        }

        return usersRepository.findDistinctByDisciplinesInAndRole(disciplines, UserRole.PROFESSOR);
    }

    public List<Users> getByName(String nameUser) {
        return usersRepository.findByName(nameUser);
    }
}
