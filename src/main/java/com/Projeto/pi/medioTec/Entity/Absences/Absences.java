package com.Projeto.pi.medioTec.Entity.Absences;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_absences")
public class Absences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classe;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Users student;

    @Column(name = "absence_count", nullable = false)
    private int absenceCount;

    @Column(name = "presence_count", nullable = false)
    private int presenceCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public Users getStudent() {
        return student;
    }

    public void setStudent(Users student) {
        this.student = student;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
    }

    public int getPresenceCount() {
        return presenceCount;
    }

    public void setPresenceCount(int presenceCount) {
        this.presenceCount = presenceCount;
    }
}
