package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.concept.CreateAlunoConceptGenericReqDto;
import com.Projeto.pi.medioTec.Dto.Request.concept.InsertConceptsAlunoGenericReqDto;
import com.Projeto.pi.medioTec.Repository.ConceptUnitOneRepository;
import com.Projeto.pi.medioTec.Repository.ConceptUnitThreeRepository;
import com.Projeto.pi.medioTec.Repository.ConceptUnitTwoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptsService {

    @Autowired
    private ConceptUnitOneRepository conceptUnitOneRepository;
    @Autowired
    private ConceptUnitTwoRepository conceptUnitTwoRepository;
    @Autowired
    private ConceptUnitThreeRepository conceptUnitThreeRepository;


    public void createConceptAlunoUnitOne(CreateAlunoConceptGenericReqDto data){
        conceptUnitOneRepository.create_aluno_concept_unit_one(data.alunoId(), data.DisciplineId());
    }

    public void insertConceptsAlunoUnitOne(InsertConceptsAlunoGenericReqDto data){
        conceptUnitOneRepository.insert_aluno_concept_unit_one(
                data.id(),
                data.av1(),
                data.av2(),
                data.nf(),
                data.status()
        );
    }

    public void createConceptAlunoUnitTwo(CreateAlunoConceptGenericReqDto data){
        conceptUnitTwoRepository.create_aluno_concept_unit_two(data.alunoId(), data.DisciplineId());
    }

    public void insertConceptsAlunoUnitTwo(InsertConceptsAlunoGenericReqDto data){
        conceptUnitTwoRepository.insert_aluno_concept_unit_two(
                data.id(),
                data.av1(),
                data.av2(),
                data.nf(),
                data.status()
        );
    }

    public void createConceptAlunoUnitThree(CreateAlunoConceptGenericReqDto data){
        conceptUnitThreeRepository.create_aluno_concept_unit_three(data.alunoId(), data.DisciplineId());
    }

    public void insertConceptsAlunoUnitThree(InsertConceptsAlunoGenericReqDto data){
        conceptUnitThreeRepository.insert_aluno_concept_unit_three(
                data.id(),
                data.av1(),
                data.av2(),
                data.nf(),
                data.status()
        );
    }
}
