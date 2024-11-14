package com.Projeto.pi.medioTec.Entity.Teams;

import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Entity.User.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_class")
public class  Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "class_id", updatable = false)
    private String id;

    @Column(length = 30, nullable = false)
    private String nameClass;

    @Column(length = 30, nullable = false)
    private String schoolYear;

    @Column(length = 20, nullable = false)
    private String shift;

    @Column(nullable = false)
    private Integer semester;


    @ManyToMany
    @JoinTable(name = "tb_turmas_disciplinas",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name ="discipline_id"))
    private Set<Disciplines> disciplines = new HashSet<>();

    @OneToMany(mappedBy = "studentClass")
    @JsonIgnore
    private Set<Users> students = new HashSet<>();

    public Classes() {
    }

    public Classes(String id) {
        this.id = id;
    }

    public Classes(String nameClass, String schoolYear, String shift, Integer semester) {
        this.nameClass = nameClass;
        this.schoolYear = schoolYear;
        this.shift = shift;
        this.semester = semester;
    }


    public Set<Disciplines> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }

    public Set<Users> getStudents() {
        return students;
    }

    public void setStudents(Set<Users> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }


}
