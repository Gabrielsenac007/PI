package com.Projeto.pi.medioTec.Controllers;


import com.Projeto.pi.medioTec.Dto.Request.AlunoRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.AssDiscAndProfReqDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.ProfessorRegisterCombineDto;
import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;
import com.Projeto.pi.medioTec.Dto.Response.AlunoDTO;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;



    @GetMapping("/allStudents")
    public ResponseEntity<List<AlunoDTO>> getAllStudents() {
        List<AlunoDTO> students = usersService.getAllStudents();
        return ResponseEntity.ok(students);
    }

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

    @PostMapping(value = "/register/student", consumes = { "multipart/form-data" })
    public ResponseEntity<String> insertStudent(
            @RequestParam String cpf,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String classeId,
            @RequestParam(required = false) MultipartFile imgProfile) {

        // Criar o DTO manualmente
        AlunoRegisterRequestDto request = new AlunoRegisterRequestDto(
                cpf, name, email, password, classeId
        );

        // Passar o DTO para o serviço
        usersService.insertStudent(request, imgProfile);

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

    @DeleteMapping("/delete/coordinator/{id}")
    public ResponseEntity<?> deleteCoordinato(@PathVariable String id){
        usersService.deleteCoordinator(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Coordenador deletado com sucesso");
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

    @PutMapping("/update/coordinator/{id}")
    public ResponseEntity<String> updateCoordinator(@PathVariable String id, @RequestBody UserRegisterRequestDto request) {
        Users updatedUser = usersService.updateCoordinator(id, request);
        return ResponseEntity.ok().body("Coordinator de ID: " + id + " foi atualizado com sucesso.");
    }

    @GetMapping("/students/class/{classId}")
    public ResponseEntity<List<Users>> getAllStudentsByClassId(@PathVariable String classId) {
        List<Users> students = usersService.getAllStudentsByClassId(classId);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/professors/class/{classId}")
    public ResponseEntity<List<Users>> getProfessorsByClassId(@PathVariable String classId) {
        List<Users> professors = usersService.getProfessorsByClassId(classId);
        if (professors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professors);
    }

    @GetMapping("alunos/findByNome/{nameUser}")
    public ResponseEntity<List<Users>> getByNameAluno(@PathVariable String nameUser) {
        return ResponseEntity.ok(usersService.getByName(nameUser));
    }

    @GetMapping("professor/findByNome/{nameUser}")
    public ResponseEntity<List<Users>> getByNameProfessor(@PathVariable String nameUser) {
        return ResponseEntity.ok(usersService.getByName(nameUser));
    }

    @GetMapping("coordenador/findByNome/{nameUser}")
    public ResponseEntity<List<Users>> getByNameCoordenador(@PathVariable String nameUser) {
        return ResponseEntity.ok(usersService.getByName(nameUser));
    }


}
