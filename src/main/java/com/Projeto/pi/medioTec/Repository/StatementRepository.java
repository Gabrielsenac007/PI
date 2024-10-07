package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.Statements.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, String> {
    List<Statement> findAllById(String id);
}
