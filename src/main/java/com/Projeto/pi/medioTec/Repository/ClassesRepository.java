package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ClassesRepository extends JpaRepository<Classes, String> {


    @Procedure
    void associate_classes_and_disciplines(@Param("p_class_id") String classId,
                                           @Param("p_discipline_id") String disciplineId
    );

    void deleteById(String id);

    @Procedure
    void update_class(@Param("p_class_id") String id,
                      @Param("p_name_class") String name,
                      @Param("p_school_year") String schoolYear,
                      @Param("p_semester") Integer semester,
                      @Param("p_shift") String shift);

}
