package org.example.model;

public enum StatusMaquina {
    OPERACIONAL("Operacional"),
    EM_MANUTENCAO("Em manutencão");


    private String status;

    StatusMaquina(String status){
        this.status=status;
    }

public String getStatus(){
        return status;
}

@Override
    public String toString(){
        return status;
    }

}
