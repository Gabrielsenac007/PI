package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertDisciplineReqDto;
import com.Projeto.pi.medioTec.Dto.Request.Coordinator.RemoveProfessorDiscDto;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import com.Projeto.pi.medioTec.Service.DisciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplinesController {

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    @Autowired
    private DisciplinesService disciplinesService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Disciplines>> getAllDisciplines(){
        List<Disciplines> listAllDisciplines = disciplinesService.getAllDisciplines();
        if(listAllDisciplines.isEmpty()){
            return ResponseEntity.badRequest().body(listAllDisciplines);
        }
        return ResponseEntity.ok().body(listAllDisciplines);
    }


    @PostMapping("/insertDiscipline")
    public ResponseEntity<?> insertDiscipline(@RequestBody InsertDisciplineReqDto request){
        disciplinesService.insertDiscipline(request);
        return ResponseEntity.status(HttpStatus.OK).body("Disciplina criada com sucesso!");

    }

    //Nao testado
    @PostMapping("/{userId}/addDiscipline/{disciplineId}")
    public ResponseEntity<String> addDisciplineToProfessor(@PathVariable String userId, @PathVariable String disciplineId) {
        disciplinesService.addDisciplineToProfessor(userId, disciplineId);
        return ResponseEntity.ok("Disciplina adicionada com sucesso ao professor!");
    }

    //Nao testado
    @PutMapping("/{userId}/updateDisciplines")
    public ResponseEntity<String> updateDisciplinesOfProfessor(@PathVariable String userId, @RequestBody List<String> disciplineIds) {
        disciplinesService.updateDisciplinesOfProfessor(userId, disciplineIds);
        return ResponseEntity.ok("Disciplinas do professor atualizadas com sucesso!");
    }

    @DeleteMapping("/removeAssoci")
    public ResponseEntity<?> removeProfessorDiscipline(@RequestBody RemoveProfessorDiscDto request){
        disciplinesRepository.remove_professor_discipline(request.professorId(), request.disciplineId());
        return ResponseEntity.ok("Associção removida com sucesso");
    }

    @GetMapping("/{userId}/getDisciplines")
    public ResponseEntity<List<Disciplines>> getDisciplinesByProfessorId(@PathVariable String userId) {
        List<Disciplines> disciplines = disciplinesService.getDisciplinesByProfessorId(userId);
        if (disciplines.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 caso não tenha disciplinas
        }
        return ResponseEntity.ok(disciplines); // Retorna as disciplinas associadas
    }

}
