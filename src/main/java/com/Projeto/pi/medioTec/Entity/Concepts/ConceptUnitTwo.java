package com.Projeto.pi.medioTec.Entity.Concepts;


import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

@Entity
@Table(name =  "tb_concpts_unit_two")
public class ConceptUnitTwo {

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

    @Enumerated(EnumType.STRING)
    private Concepts AV1;

    @Enumerated(EnumType.STRING)
    private Concepts AV2;


    @Enumerated(EnumType.STRING)
    private FinalStatus status;

    public ConceptUnitTwo(String id, Users aluno, Disciplines discipline, Concepts AV1, Concepts AV2, FinalStatus status) {
        this.id = id;
        this.aluno = aluno;
        this.discipline = discipline;
        this.AV1 = AV1;
        this.AV2 = AV2;
        this.status = status;
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

    public Concepts getAV1() {
        return AV1;
    }

    public void setAV1(Concepts AV1) {
        this.AV1 = AV1;
    }

    public Concepts getAV2() {
        return AV2;
    }

    public void setAV2(Concepts AV2) {
        this.AV2 = AV2;
    }

    public FinalStatus getStatus() {
        return status;
    }

    public void setStatus(FinalStatus status) {
        this.status = status;
    }
}
