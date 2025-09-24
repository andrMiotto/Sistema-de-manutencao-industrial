package org.example.model;

public class Tecnico {
    private int id;
    private String nome;
    private String especialdiade;


    public Tecnico(int id, String nome, String especialdiade) {
        this.id = id;
        this.nome = nome;
        this.especialdiade = especialdiade;
    }

    public Tecnico(String nome, String especialdiade) {
        this.nome = nome;
        this.especialdiade = especialdiade;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialdiade() {
        return especialdiade;
    }

    public void setEspecialdiade(String especialdiade) {
        this.especialdiade = especialdiade;
    }


}
