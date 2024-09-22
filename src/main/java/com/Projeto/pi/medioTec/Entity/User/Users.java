package com.Projeto.pi.medioTec.Entity.User;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id",updatable = false, unique = true)
    private String id;

    @Column(length = 15, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100, nullable = false )
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    private UserRole role;

    public Users() {
    }


    public Users(String cpf, String name, String email, String password, UserRole role) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.role == UserRole.ADMIN) {
            // Admin recebe todas as roles
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_COORDENADOR"));
            authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ALUNO"));
        } else if (this.role == UserRole.COORDENADOR) {
            authorities.add(new SimpleGrantedAuthority("ROLE_COORDENADOR"));
        } else if (this.role == UserRole.PROFESSOR) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }

        return authorities;

    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
