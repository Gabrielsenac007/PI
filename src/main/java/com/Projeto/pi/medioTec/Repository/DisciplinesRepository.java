package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisciplinesRepository extends JpaRepository<Disciplines, UUID> {
}
