package org.example.model;

import java.time.LocalDate;

public class OrdemManutencao {
    private int id;
    private int idMaquina;
    private int idTecnico;
    private LocalDate dataSolicitacao;
    private StatusOrdemManutencao status;

    public OrdemManutencao(int idMaquina, int idTecnico) {
        this.idMaquina = idMaquina;
        this.idTecnico = idTecnico;
        this.dataSolicitacao = LocalDate.now();
        this.status = StatusOrdemManutencao.PENDENTE;
    }

    public OrdemManutencao(int id, int idMaquina, int idTecnico, LocalDate dataSolicitacao, StatusOrdemManutencao status) {
        this.id = id;
        this.idMaquina = idMaquina;
        this.idTecnico = idTecnico;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public OrdemManutencao(int id, int idMaquina, int idTecnico) {
        this.id = id;
        this.idMaquina = idMaquina;
        this.idTecnico = idTecnico;

    }


    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusOrdemManutencao getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemManutencao status) {
        this.status = status;
    }
}
