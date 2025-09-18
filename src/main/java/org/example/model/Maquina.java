package org.example.model;

public class Maquina {
    private int id;
    private String nome;
    private String setor;
    private StatusMaquina status;

    public Maquina(int id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.status = StatusMaquina.OPERACIONAL;
    }

    public Maquina(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
        this.status = StatusMaquina.OPERACIONAL;
    }



    public StatusMaquina getStatus() {
        return status;
    }

    public void setStatus(StatusMaquina status) {
        this.status = status;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
