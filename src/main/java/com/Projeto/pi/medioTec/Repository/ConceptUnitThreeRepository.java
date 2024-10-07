package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitThree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ConceptUnitThreeRepository extends JpaRepository<ConceptUnitThree, String> {



    @Procedure
    void insert_aluno_concept_unit_three(@Param ("p_id") String id,
                                         @Param ("p_av1") String av1 ,
                                         @Param ("p_av2") String av2,
                                         @Param ("p_status") String status,
                                         @Param ("p_aluno_id") String alunoId,
                                         @Param ("p_discipline_id") String disciplineId
    );

}
