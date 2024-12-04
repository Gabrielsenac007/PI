package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface DisciplinesRepository extends JpaRepository<Disciplines, String> {


    @Procedure
    void associate_professor_and_discipline(@Param("p_professor_id") String professorId,
                                            @Param("p_disciplina_id") String disciplineId
    );

    @Procedure
    void remove_professor_discipline(@Param("p_professor_id") String professorId,
                                     @Param("p_discipline_id") String disciplineId
    );

    List<Disciplines> findByProfessors_Id(String professorId);



}
