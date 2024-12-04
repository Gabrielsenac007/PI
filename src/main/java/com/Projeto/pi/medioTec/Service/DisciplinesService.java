package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertDisciplineReqDto;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import com.Projeto.pi.medioTec.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DisciplinesService {

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Disciplines> getAllDisciplines(){
        return disciplinesRepository.findAll();
    }


    public  void insertDiscipline(InsertDisciplineReqDto data){
        Disciplines newDiscipline = new Disciplines(data.disciplineName(), data.description());
        disciplinesRepository.save(newDiscipline);
    }


    //Nao testado
    public void addDisciplineToProfessor(String userId, String disciplineId) {
        Users professor = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplines discipline = disciplinesRepository.findById(disciplineId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        professor.getDisciplines().add(discipline);
        disciplinesRepository.save(discipline);
        usersRepository.save(professor);
    }

    //Nao testado
    public void updateDisciplinesOfProfessor(String userId, List<String> disciplineIds) {
        Users professor = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Set<Disciplines> newDisciplines = new HashSet<>();
        for (String disciplineId : disciplineIds) {
            Disciplines discipline = disciplinesRepository.findById(disciplineId)
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            newDisciplines.add(discipline);
        }
        professor.setDisciplines(newDisciplines);
        usersRepository.save(professor);
    }

    public List<Disciplines> getDisciplinesByProfessorId(String userId) {
        return disciplinesRepository.findByProfessors_Id(userId); // Chama o repositório
    }

}
