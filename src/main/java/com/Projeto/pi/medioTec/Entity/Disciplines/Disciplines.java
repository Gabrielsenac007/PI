package com.Projeto.pi.medioTec.Entity.Disciplines;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tb_disciplines")
public class Disciplines {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "discipline_id", updatable = false)
    private UUID id;

    @Column(length = 100, nullable = true)
    private String disciplineName;

    @Column(length = 500)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
