package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Service.DisciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
