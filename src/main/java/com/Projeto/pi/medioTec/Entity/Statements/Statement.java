package com.Projeto.pi.medioTec.Entity.Statements;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "statement_id", updatable = false, unique = true)
    private String id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Users creator;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false) // Nome da coluna correspondente ao relacionamento
    private Classes classes; // Mudando o nome do atributo para "classes" para seguir a convenção

    public Statement(String title, String content, Users users, String s) {
    }

    public Statement(String title, String content, Users creator, Classes classes) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.classes = classes;
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Users getCreator() {
        return creator;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
