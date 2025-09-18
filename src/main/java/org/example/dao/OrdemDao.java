package org.example.dao;

import org.example.model.OrdemManutencao;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdemDao {

    public OrdemManutencao criarOrdem(OrdemManutencao ordemManutencao) throws SQLException {
        String query = "INSERT INTO OrdemManutencao(idMaquina,idTecnico,dataSolicitacao,status) VALUES (?,?,?,?)";
        String queryMaquina ="UPDATE Maquina SET status = ? WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);
             PreparedStatement stmtUpdate = connection.prepareStatement(queryMaquina)){

            stmt.setInt(1, ordemManutencao.getIdMaquina());
            stmt.setInt(2, ordemManutencao.getIdTecnico());
            stmt.setDate(3, Date.valueOf(ordemManutencao.getDataSolicitacao()));
            stmt.setString(4, ordemManutencao.getStatus().name());

            stmt.executeUpdate();

            stmtUpdate.setString(1, "EM_MANUTENCAO");
            stmtUpdate.setInt(2, ordemManutencao.getIdMaquina());

            stmt.executeUpdate();
        }



        return ordemManutencao;
    }


}
