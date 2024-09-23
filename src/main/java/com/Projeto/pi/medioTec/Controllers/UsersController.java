package com.Projeto.pi.medioTec.Controllers;


import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


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
