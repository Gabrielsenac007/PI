package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.concept.CreateAlunoConceptUnitOneReqDto;
import com.Projeto.pi.medioTec.Dto.Request.concept.InsertConceptsAlunoUnitOneReqDto;
import com.Projeto.pi.medioTec.Service.ConceptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/concepts")
public class ConceptsController {

    @Autowired
    private ConceptsService conceptsService;

    @PostMapping("/creteConceptUnitOne")
    public ResponseEntity<?> createAlunoConceptUnitOne(@RequestBody CreateAlunoConceptUnitOneReqDto request){
        conceptsService.createConceptAlunoUnitOne(request);
        return ResponseEntity.status(HttpStatus.OK).body("Tabela de conceito do aluno criada com sucesso!");
    }

    @PutMapping("/insertConceptUnitOne")
    public ResponseEntity<?> insertConceptUnitOne(@RequestBody InsertConceptsAlunoUnitOneReqDto request){
        conceptsService.insertConceptsAlunoUnitOne(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito inserido com sucesso!");
    }

}
