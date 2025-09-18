package org.example.model;

public enum StatusOrdemManutencao {
    PENDENTE("Pendente"),
    EXECUTADA("Executada"),
    CANCELADA("Cancelada");

    private String status;

    StatusOrdemManutencao(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
