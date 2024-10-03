package com.Projeto.pi.medioTec.Controllers;


import com.Projeto.pi.medioTec.Dto.Request.Coordinator.AssDiscAndProfReqDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertDisciplineReqDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.ProfessorRegisterCombineDto;
import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;



    @GetMapping("/allStudents")
    public ResponseEntity<List<Users>> getAllStudents(){
        List<Users> students = usersService.getAllStudents();
        return ResponseEntity.ok(students);
    };

    @GetMapping("/allProfessor")
    public ResponseEntity<List<Users>> getAllProfessor(){
        List<Users> professor = usersService.getAllProfessor();
        return ResponseEntity.ok(professor);
    };

    @GetMapping("/allCoordenador")
    public ResponseEntity<List<Users>> getAllCoordenador(){
        List<Users> coordenador = usersService.getAllCoordenador();
        return ResponseEntity.ok(coordenador);
    };


    @PostMapping("/register/coordinator")
    public ResponseEntity<String> insertCoordinator(@RequestBody UserRegisterRequestDto request) {
        usersService.insertCoordinator(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coordenador registrado com sucesso!");
    }

    @PostMapping("/register/professor")
    public ResponseEntity<String> insertProfessor(@RequestBody ProfessorRegisterCombineDto request) {

        UserRegisterRequestDto professor = request.professor();
        List<AssDiscAndProfReqDto> disciplina = request.disciplina();

        if (disciplina == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A lista de disciplinas não pode ser nula");
        }


        usersService.insertProfessor(professor, disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body("Professor registrado com sucesso!");
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> insertStudent(@RequestBody UserRegisterRequestDto request) {
        usersService.insertStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno registrado com sucesso!");
    }

    @DeleteMapping("/delete/professor/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable String id){
        usersService.deleteProfessor(id);
        return ResponseEntity.ok().body("O Professor de ID: " + id + " Foi deletado");
    }

    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id){
        usersService.deleteStudent(id);
        return ResponseEntity.ok().body("O Student de ID: " + id + " Foi deletado");
    }

    @PutMapping("/update/professor/{id}")
    public ResponseEntity<String> updateProfessor(@PathVariable String id, @RequestBody UserRegisterRequestDto request) {
        Users updatedUser = usersService.updateProfessor(id, request);
        return ResponseEntity.ok().body("Usuário de ID: " + id + " foi atualizado com sucesso.");
    }

    @PutMapping("/update/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable String id, @RequestBody UserRegisterRequestDto request) {
        Users updatedUser = usersService.updateStudent(id, request);
        return ResponseEntity.ok().body("Usuário de ID: " + request.name() + " foi atualizado com sucesso.");
    }


}
