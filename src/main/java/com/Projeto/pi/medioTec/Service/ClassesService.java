package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertClassReqDto;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    public void insertClass(InsertClassReqDto data){

        Classes newClasses = new Classes(data.nameClass(), data.schoolYear(), data.shift(), data.semester());
        classesRepository.save(newClasses);

    }

}
