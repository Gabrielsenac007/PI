package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Week.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    List<Week> findByClasseId(String classId);
}
