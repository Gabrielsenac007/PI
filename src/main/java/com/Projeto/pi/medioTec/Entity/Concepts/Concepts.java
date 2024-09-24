package com.Projeto.pi.medioTec.Entity.Concepts;

public enum Concepts {

    A("atendido"),
    PA("pacialmente atendido"),
    NA("nao atendido");

    private String concepts;

    Concepts(String concepts){
        this.concepts = concepts;
    }

    public String getConcepts() {
        return concepts;
    }

    public void setConcepts(String concepts) {
        this.concepts = concepts;
    }
}
