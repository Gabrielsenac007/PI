package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.concept.CreateAlunoConceptGenericReqDto;
import com.Projeto.pi.medioTec.Dto.Request.concept.InsertConceptsAlunoGenericReqDto;
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
    public ResponseEntity<?> createAlunoConceptUnitOne(@RequestBody CreateAlunoConceptGenericReqDto request){
        conceptsService.createConceptAlunoUnitOne(request);
        return ResponseEntity.status(HttpStatus.OK).body("Tabela de conceito do aluno criada com sucesso!");
    };

    @PutMapping("/insertConceptUnitOne")
    public ResponseEntity<?> insertConceptUnitOne(@RequestBody InsertConceptsAlunoGenericReqDto request){
        conceptsService.insertConceptsAlunoUnitOne(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito inserido com sucesso!");
    };

    @PostMapping("/creteConceptUnitTwo")
    public ResponseEntity<?> createAlunoConceptUnitTwo(@RequestBody CreateAlunoConceptGenericReqDto request){
        conceptsService.createConceptAlunoUnitTwo(request);
        return ResponseEntity.status(HttpStatus.OK).body("Tabela de conceito unidade dois do aluno criada com sucesso!");
    };

    @PutMapping("/insertConceptUnitTwo")
    public ResponseEntity<?> insertConceptUnitTwo(@RequestBody InsertConceptsAlunoGenericReqDto request) {
        conceptsService.insertConceptsAlunoUnitTwo(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito unidade dois inserido com sucesso!");
    };

    @PostMapping("/creteConceptUnitThree")
    public ResponseEntity<?> createAlunoConceptUnitThree(@RequestBody CreateAlunoConceptGenericReqDto request){
        conceptsService.createConceptAlunoUnitThree(request);
        return ResponseEntity.status(HttpStatus.OK).body("Tabela de conceito unidade tres do aluno criada com sucesso!");
    };

    @PutMapping("/insertConceptUnitThree")
    public ResponseEntity<?> insertConceptUnitThree(@RequestBody InsertConceptsAlunoGenericReqDto request) {
        conceptsService.insertConceptsAlunoUnitThree(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito unidade tres inserido com sucesso!");
    };


}
