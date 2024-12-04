package com.Projeto.pi.medioTec.Entity.User;

import com.Projeto.pi.medioTec.Entity.Absences.Absences;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_user")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, unique = true)
    private String id;

    @Column(length = 15, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 255)
    private String imgProfile;

    @ManyToOne
    @JsonBackReference // Relacionamento inverso com Classes
    @JoinColumn(name = "class_id")
    private Classes studentClass;

    @Enumerated(EnumType.STRING) // Persistir como String legível
    @Column(nullable = false)
    private UserRole role;

    @ManyToMany
    @JoinTable(name = "tb_professores_disciplinas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private Set<Disciplines> disciplines = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference // Relacionamento direto com Absences
    private List<Absences> absences = new ArrayList<>();

    // Construtores
    public Users() {
    }

    public Users(String cpf, String name, String email, String password, UserRole role) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users(String cpf, String name, String email, String password, Classes studentClass, UserRole role, String imgProfile) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentClass = studentClass;
        this.role = role;
        this.imgProfile = imgProfile;
    }

    // Getters e Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public Classes getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Classes studentClass) {
        this.studentClass = studentClass;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<Disciplines> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }

    public List<Absences> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absences> absences) {
        this.absences = absences;
    }

    // Implementação de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.role == UserRole.ADMIN) {
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
}
