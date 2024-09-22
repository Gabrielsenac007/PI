package com.Projeto.pi.medioTec.Entity.User;

public enum UserRole {
    ADMIN("admin"),
    COORDENADOR("coordenador"),
    PROFESSOR("professor"),
    ALUNO("aluno");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
