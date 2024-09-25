package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertDisciplineReqDto;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinesService {

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    public List<Disciplines> getAllDisciplines(){
        return disciplinesRepository.findAll();
    }


    public  void insertDiscipline(InsertDisciplineReqDto data){
        Disciplines newDiscipline = new Disciplines(data.disciplineName(), data.description());
        disciplinesRepository.save(newDiscipline);
    }

}
