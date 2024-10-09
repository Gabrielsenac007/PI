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
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequestDto request) {
        try {
            LoginResponseDto response = usersService.login(request);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
