package com.Projeto.pi.medioTec.Entity.Week;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tb_week")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "week_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classe;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Disciplines discipline;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "registration_id", nullable = false)
    private Long registrationId;

    // Getters e Setters
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

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }
}
