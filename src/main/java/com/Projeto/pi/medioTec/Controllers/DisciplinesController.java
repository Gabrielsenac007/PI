package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertDisciplineReqDto;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Service.DisciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplinesController {

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


}
