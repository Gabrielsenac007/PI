package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Entity.Absences.Absences;
import com.Projeto.pi.medioTec.Service.AbsencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsencesController {

    @Autowired
    private AbsencesService absencesService;

    /**
     * Lista todas as ausências registradas no sistema.
     *
     * @return Lista de ausências.
     */
    @GetMapping("/listAllAbsences")
    public ResponseEntity<List<Absences>> listAllAbsences() {
        try {
            List<Absences> allAbsences = absencesService.listAllAbsences();
            return ResponseEntity.ok(allAbsences);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Retorna null no corpo da resposta.
        }
    }

    /**
     * Incrementa a presença de um aluno.
     *
     * @param studentId ID do aluno.
     * @return Status da operação.
     */
    @PostMapping("/addPresence/{studentId}")
    public ResponseEntity<String> addPresence(@PathVariable String studentId) {
        try {
            absencesService.addPresence(studentId);
            return ResponseEntity.ok("Presença adicionada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao adicionar presença.");
        }
    }

    /**
     * Incrementa a falta de um aluno.
     *
     * @param studentId ID do aluno.
     * @return Status da operação.
     */
    @PostMapping("/addAbsence/{studentId}")
    public ResponseEntity<String> addAbsence(@PathVariable String studentId) {
        try {
            absencesService.addAbsence(studentId);
            return ResponseEntity.ok("Falta adicionada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao adicionar falta.");
        }
    }

    /**
     * Cria um registro de ausências para o aluno.
     *
     * @param studentId ID do aluno.
     * @return Status da operação.
     */
    @PostMapping("/createAbsencesRecord/{studentId}")
    public ResponseEntity<String> createAbsencesRecord(@PathVariable String studentId) {
        try {
            absencesService.createAbsencesRecord(studentId);
            return ResponseEntity.ok("Registro de ausências criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {  // Tratar erro específico do serviço.
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao criar registro de ausências.");
        }
    }
}
