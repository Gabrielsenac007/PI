package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.concept.CreateAlunoConceptGenericReqDto;
import com.Projeto.pi.medioTec.Dto.Request.concept.InsertConceptsAlunoGenericReqDto;
import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitOne;
import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitThree;
import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitTwo;
import com.Projeto.pi.medioTec.Service.ConceptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concepts")
public class ConceptsController {

    @Autowired
    private ConceptsService conceptsService;



    @PostMapping("/insertConceptUnitOne")
    public ResponseEntity<?> insertConceptUnitOne(@RequestBody InsertConceptsAlunoGenericReqDto request){
        conceptsService.insertConceptsAlunoUnitOne(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito inserido com sucesso!");
    };



    @PostMapping("/insertConceptUnitTwo")
    public ResponseEntity<?> insertConceptUnitTwo(@RequestBody InsertConceptsAlunoGenericReqDto request) {
        conceptsService.insertConceptsAlunoUnitTwo(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito unidade dois inserido com sucesso!");
    };



    @PostMapping("/insertConceptUnitThree")
    public ResponseEntity<?> insertConceptUnitThree(@RequestBody InsertConceptsAlunoGenericReqDto request) {
        conceptsService.insertConceptsAlunoUnitThree(request);
        return ResponseEntity.status(HttpStatus.OK).body("Conceito unidade tres inserido com sucesso!");
    };

    @GetMapping("/getUnitOne")
    public ResponseEntity<List<ConceptUnitOne>> getUnitOne() {
        List<ConceptUnitOne> listConceptsUnitOne = conceptsService.getUnitOne();
        return ResponseEntity.ok(listConceptsUnitOne);
    }

    @GetMapping("/getUnitTwo")
    public ResponseEntity<List<ConceptUnitTwo>> getUnitTwo() {
        List<ConceptUnitTwo> listConceptsUnitTwo = conceptsService.getUnitTwo();
        return ResponseEntity.ok(listConceptsUnitTwo);
    }

    @GetMapping("/getUnitThree")
    public ResponseEntity<List<ConceptUnitThree>> getUnitThree() {
        List<ConceptUnitThree> listConceptsUnitThree = conceptsService.getUnitThree();
        return ResponseEntity.ok(listConceptsUnitThree);
    }

    @GetMapping("/class/{classId}/discipline/{disciplineId}")
    public ResponseEntity<List<ConceptUnitOne>> getConceptsByClassAndDiscipline(
            @PathVariable String classId,
            @PathVariable String disciplineId) {

        List<ConceptUnitOne> concepts = conceptsService.getConceptsByClassAndDiscipline(classId, disciplineId);
        return ResponseEntity.ok(concepts);
    }

}
