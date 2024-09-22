package com.Projeto.pi.medioTec.Controllers;


import com.Projeto.pi.medioTec.Dto.Request.UserAuthenticationRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.getCpf;
import com.Projeto.pi.medioTec.Dto.Response.LoginResponseDto;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/user/{cpf}")
    public ResponseEntity<Users> getUserByCpf(@PathVariable String cpf) {
        try {
            Users user = usersService.getByCpf(cpf);
            return ResponseEntity.ok(user); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequestDto request){
        try {
            String token = usersService.login(request);
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }

    @PostMapping("/register/coordinator")
    public ResponseEntity<String> insertCoordinator(@RequestBody UserRegisterRequestDto request) {
        usersService.insertCoordinator(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coordenador registrado com sucesso!");
    }

    @PostMapping("/register/professor")
    public ResponseEntity<String> insertProfessor(@RequestBody UserRegisterRequestDto request) {
        usersService.insertProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Professor registrado com sucesso!");
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> insertStudent(@RequestBody UserRegisterRequestDto request) {
        usersService.insertStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno registrado com sucesso!");
    }
}
