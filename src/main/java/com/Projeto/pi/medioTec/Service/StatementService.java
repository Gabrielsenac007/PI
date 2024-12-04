package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.Request.InsertStatementRequesteDto;
import com.Projeto.pi.medioTec.Entity.Statements.Statement;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.Projeto.pi.medioTec.Repository.StatementRepository;
import com.Projeto.pi.medioTec.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Statement> getAllStatement(){
       return statementRepository.findAll();
    }


    public void insertStatement(InsertStatementRequesteDto data){

        Optional<Users> creator = usersRepository.findById(data.creatorId());
        if (data.creatorId() == null || data.creatorId().isEmpty()) {
            throw new IllegalArgumentException("Usuário não fornecido");
        }

        Users users = creator.get();

        Statement statement = new Statement(data.title(), data.content(), users,  data.classId());
        statementRepository.save(statement);

    }

    public List<Statement> getAllStatementsOfUser(String id) {
        List<Statement> listStatement = statementRepository.findAllById(id);
        return listStatement;
    }
}
