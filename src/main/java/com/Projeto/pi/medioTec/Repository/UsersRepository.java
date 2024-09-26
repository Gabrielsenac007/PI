package com.Projeto.pi.medioTec.Repository;

import com.Projeto.pi.medioTec.Entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, String> {

    UserDetails findByCpf(String cpf);

    void deleteById(UUID id);

    Optional<Users> findById(UUID id);
}
