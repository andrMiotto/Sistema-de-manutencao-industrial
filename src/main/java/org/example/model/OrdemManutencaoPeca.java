package org.example.model;

import java.time.LocalDate;

public class OrdemManutencaoPeca extends OrdemManutencao {

    private String nomeMaquina;
    private String nomeTecnico;

    public OrdemManutencaoPeca(int id, int idMaquina, int idTecnico, LocalDate dataSolicitacao, String status, String nomeMaquina, String nomeTecnico) {
        super(id, idMaquina, idTecnico, dataSolicitacao, StatusOrdemManutencao.valueOf(status));
        this.nomeMaquina = nomeMaquina;
        this.nomeTecnico = nomeTecnico;
    }
}