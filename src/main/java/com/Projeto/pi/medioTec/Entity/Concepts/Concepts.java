package com.Projeto.pi.medioTec.Entity.Concepts;


import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_concepts")
public class Concepts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "concept_id",updatable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Users aluno;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Disciplines discipline;

    @Column(length = 15, nullable = false )
    private String conceito;

    public Concepts() {
    }

    public Concepts(String id, Users aluno, Disciplines discipline, String conceito) {
        this.id = id;
        this.aluno = aluno;
        this.discipline = discipline;
        this.conceito = conceito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getAluno() {
        return aluno;
    }

    public void setAluno(Users aluno) {
        this.aluno = aluno;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }
}
