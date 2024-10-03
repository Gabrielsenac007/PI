package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.AssDiscAndProfReqDto;
import com.Projeto.pi.medioTec.Dto.Request.UserAuthenticationRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.getCpf;
import com.Projeto.pi.medioTec.Dto.Response.LoginResponseDto;
import com.Projeto.pi.medioTec.Entity.User.UserRole;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import com.Projeto.pi.medioTec.Repository.UsersRepository;
import com.Projeto.pi.medioTec.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public LoginResponseDto login(UserAuthenticationRequestDto data) {
        if (data.cpf() == null || data.cpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não fornecido");
        }

        UserDetails userDetails = usersRepository.findByCpf(data.cpf());
        if (userDetails == null) {
            throw new UsernameNotFoundException("CPF não encontrado");
        }

        var userLogin = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        try {
            var auth = this.authenticationManager.authenticate(userLogin);
            Users user = (Users) auth.getPrincipal();
            String token = tokenService.generateToken(user);
            return new LoginResponseDto(token, user.getName(), user.getRole());
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


    public List<Users> getAllStudents(){
        return usersRepository.findByRole(UserRole.ALUNO);
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

    public void insertProfessor(UserRegisterRequestDto register, List<AssDiscAndProfReqDto> disciplines){
        createProfessor(register, disciplines,UserRole.PROFESSOR);
    }

    public void insertCoordinator(UserRegisterRequestDto register){
        createUserWithRole(register, UserRole.COORDENADOR );
    }


    public void insertStudent(UserRegisterRequestDto register){
        createUserWithRole(register, UserRole.ALUNO);
    }

    public void deleteProfessor(String id) {
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
}
