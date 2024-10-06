package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.Request.InsertStatementRequesteDto;
import com.Projeto.pi.medioTec.Entity.Statements.Statement;
import com.Projeto.pi.medioTec.Service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @GetMapping("/getAllStatements")
    public ResponseEntity<List<Statement>> getAll(){
        List<Statement> statements =  statementService.getAllStatement();
        return ResponseEntity.ok(statements);
    }

    @PostMapping("/insertStatement")
    public ResponseEntity<?> insertStatement(@RequestBody InsertStatementRequesteDto request){

        statementService.insertStatement(request);
        return ResponseEntity.status(HttpStatus.OK).body("Aviso creiado com sucesso!");
    }

}
