package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.concept.CreateAlunoConceptUnitOneReqDto;
import com.Projeto.pi.medioTec.Dto.Request.concept.InsertConceptsAlunoUnitOneReqDto;
import com.Projeto.pi.medioTec.Repository.ConceptUnitOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptsService {

    @Autowired
    private ConceptUnitOneRepository conceptUnitOneRepository;


    public void createConceptAlunoUnitOne(CreateAlunoConceptUnitOneReqDto data){
        conceptUnitOneRepository.create_aluno_concept_unit_one(data.alunoId(), data.DisciplineId());
    }

    public void insertConceptsAlunoUnitOne(InsertConceptsAlunoUnitOneReqDto data){
        conceptUnitOneRepository.insert_aluno_concept_unit_one(
                data.id(),
                data.av1(),
                data.av2(),
                data.nf(),
                data.status()
        );
    }
}
