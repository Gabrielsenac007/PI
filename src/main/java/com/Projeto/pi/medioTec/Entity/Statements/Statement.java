package com.Projeto.pi.medioTec.Entity.Statements;


import com.Projeto.pi.medioTec.Entity.User.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "statement_id",updatable = false, unique = true)
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



    public Statement() {
    }

    public Statement(String id, String title, String content, LocalDateTime creationDate, Users creator) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.creator = creator;
    }

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
}
