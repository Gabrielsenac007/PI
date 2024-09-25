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

    public String login(UserAuthenticationRequestDto data) {

        UserDetails userDetails = usersRepository.findByCpf(data.cpf());

        if (userDetails == null) {
            throw new UsernameNotFoundException("CPF não encontrado");
        }

        if (data.cpf() != null) {
            var userLogin = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
            try {
                var auth = this.authenticationManager.authenticate(userLogin);
                return tokenService.generateToken((Users) auth.getPrincipal());
            } catch (Exception e) {
                throw new BadCredentialsException("Erro ao realizar login: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("CPF não fornecido");
    }

    public Users getByCpf(String cpf){
        Users user = (Users) usersRepository.findByCpf(cpf);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return user;
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

    private void createProfessor(UserRegisterRequestDto register, UserRole role, List<AssDiscAndProfReqDto> disciplines){

        Users user = (Users) usersRepository.findByCpf(register.cpf());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já existe já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        Users newUser = new Users(register.cpf(), register.name(), register.email(), encryptedPassword, role);
        usersRepository.save(newUser);

        for(AssDiscAndProfReqDto data : disciplines){
            disciplinesRepository.associate_professor_and_discipline(data.professorId(), data.disciplinaId());
        }

    }

    public void insertCoordinator(UserRegisterRequestDto register){
        createUserWithRole(register, UserRole.COORDENADOR );
    }

    public void insertProfessor(UserRegisterRequestDto register, List<AssDiscAndProfReqDto> disciplines){
        createProfessor(register, UserRole.PROFESSOR, disciplines);
    }

    public void insertStudent(UserRegisterRequestDto register){
        createUserWithRole(register, UserRole.ALUNO);
    }
}
