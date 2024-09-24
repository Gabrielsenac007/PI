package com.Projeto.pi.medioTec.Entity.Concepts;

public enum FinalStatus {

    D("deferido"),
    ND("nao deferido");

    private String status;

    FinalStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
