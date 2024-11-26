package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Absences.Absences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsencesRepository extends JpaRepository<Absences,  Long>{

}
