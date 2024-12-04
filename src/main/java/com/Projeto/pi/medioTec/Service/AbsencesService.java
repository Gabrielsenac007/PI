package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Entity.Absences.Absences;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Repository.AbsencesRepository;
import com.Projeto.pi.medioTec.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsencesService {

    @Autowired
    private AbsencesRepository absencesRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Incrementa o número de presenças de um aluno.
     *
     * @param studentId ID do aluno.
     */
    public void addPresence(String studentId) {
        Absences absences = absencesRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + studentId));

        absences.setPresenceCount(absences.getPresenceCount() + 1);
        absencesRepository.save(absences);
    }

    /**
     * Incrementa o número de faltas de um aluno.
     *
     * @param studentId ID do aluno.
     */
    public void addAbsence(String studentId) {
        Absences absences = absencesRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + studentId));

        absences.setAbsenceCount(absences.getAbsenceCount() + 1);
        absencesRepository.save(absences);
    }

    /**
     * Lista todos os registros de ausências.
     *
     * @return Lista de registros de ausências.
     */
    public List<Absences> listAllAbsences() {
        return absencesRepository.findAll();
    }

    /**
     * Cria um registro de ausências para o aluno.
     *
     * @param studentId ID do aluno.
     */
    public void createAbsencesRecord(String studentId) {
        Users student = usersRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + studentId));

        // Verifica se o registro de ausências já existe para este aluno
        if (absencesRepository.findByStudentId(studentId).isPresent()) {
            throw new RuntimeException("Registro de ausências já existe para este aluno.");
        }

        Absences absences = new Absences();
        absences.setStudent(student);
        absences.setAbsenceCount(0);
        absences.setPresenceCount(0);

        absencesRepository.save(absences);
    }
}
