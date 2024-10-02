package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertClassReqDto;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping("/classRegister")
    public ResponseEntity<?> classeRegister(@RequestBody InsertClassReqDto request){

        classesService.insertClass(request);
        return ResponseEntity.status(HttpStatus.OK).body("Turma registrada com sucesso!");

    }

    @GetMapping("/getAllClasses")
    public ResponseEntity<?> getAllClasses(){
        List<Classes> classesList = classesService.getAllClasses();
        if (classesList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classes não encontradas");
        }
        return ResponseEntity.ok(classesList);
    }



}
