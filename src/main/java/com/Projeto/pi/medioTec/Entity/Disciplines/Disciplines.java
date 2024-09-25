package com.Projeto.pi.medioTec.Entity.Disciplines;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_disciplines")
public class Disciplines {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "discipline_id", updatable = false, unique = true)
    private String id;

    @Column(length = 100, nullable = true)
    private String disciplineName;

    @Column(length = 500)
    private String description;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Users> professors = new HashSet<>();

    @ManyToMany(mappedBy = "disciplines")
    private Set<Classes> classes = new HashSet<>();


    public Disciplines() {
    }

    public Disciplines( String disciplineName, String description) {
        this.disciplineName = disciplineName;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
