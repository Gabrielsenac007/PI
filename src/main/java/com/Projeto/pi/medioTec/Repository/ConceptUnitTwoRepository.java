package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitOne;
import com.Projeto.pi.medioTec.Entity.Concepts.ConceptUnitTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConceptUnitTwoRepository extends JpaRepository<ConceptUnitTwo, String> {




    @Procedure
    void insert_aluno_concept_unit_two(@Param ("p_id") String id,
                                       @Param ("p_av1") String av1 ,
                                       @Param ("p_av2") String av2,
                                       @Param ("p_status") String status,
                                       @Param ("p_aluno_id") String alunoId,
                                       @Param ("p_discipline_id") String disciplineId
    );


    @Query("SELECT c FROM ConceptUnitTwo c WHERE c.aluno.studentClass.id = :classId AND c.discipline.id = :disciplineId")
    List<ConceptUnitTwo> findConceptsByClassAndDiscipline(@Param("classId") String classId, @Param("disciplineId") String disciplineId);


}
