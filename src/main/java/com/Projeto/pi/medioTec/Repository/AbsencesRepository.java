package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Absences.Absences;
import com.Projeto.pi.medioTec.Entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsencesRepository extends JpaRepository<Absences,  Long>{
    Optional<Absences> findByStudentId(String studentId);
    @Query(value = "CALL GetUserDetails(:userId)", nativeQuery = true)
    List<Object[]> getUserDetails(@Param("userId") Long userId);
}
