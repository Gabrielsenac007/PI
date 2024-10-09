package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.Coordinator.InsertClassReqDto;
import com.Projeto.pi.medioTec.Dto.Request.classes.AssociateClassesAndDisciplinesReqDto;
import com.Projeto.pi.medioTec.Dto.Request.classes.UpdateClassReqDto;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;

@Service
public class ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    public List<Classes> getAllClasses(){
        return classesRepository.findAll();
    }

    public void insertClass(InsertClassReqDto data){

        Classes newClasses = new Classes(data.nameClass(), data.schoolYear(), data.shift(), data.semester());
        classesRepository.save(newClasses);

    }

    public void associateDisciplines(AssociateClassesAndDisciplinesReqDto data){
        classesRepository.associate_classes_and_disciplines(data.classeId(), data.disciplineId());
    }

    public void deleteClass(String id){
        classesRepository.deleteById(id);
    }

    public void updateClass(UpdateClassReqDto data){
        classesRepository.update_class(data.id(), data.name(), data.schoolYear(), data.semester(), data.shift());
    }

}
