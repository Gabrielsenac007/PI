package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface DisciplinesRepository extends JpaRepository<Disciplines, String> {


    void associate_professor_and_discipline(@Param("p_professor_id") String professorId,
                                            @Param("p_disciplina_id") String disciplineId
    );


}
