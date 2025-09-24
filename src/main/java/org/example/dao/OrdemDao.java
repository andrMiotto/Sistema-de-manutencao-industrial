package org.example.dao;

import org.example.model.OrdemManutencao;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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



    public List<OrdemManutencao> listarTodas() throws SQLException{
        String query="""
          SELECT O.id
                ,O.idMaquina
                ,M.nome as nomeMaquina
                ,O.idTecnico,
                T.nome as nomeTecnico
                FROM OrdemManutencao O
                JOIN Maquina M
                On O.idMaquina = M.id
                JOIN Tecnico T
                ON O.idTecnico = T.id
                WHERE O.status = 'PENDENTE';
                """;


        List<OrdemManutencao> ordens = new ArrayList<>();


        try(Connection connection = Conexao.conectar();
        PreparedStatement stmt = connection.prepareStatement(query);){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int idMaquina = rs.getInt("idMaquina");
                int idTecnico = rs.getInt("idTecnico");

                OrdemManutencao ordem = new OrdemManutencao(id,idMaquina,idTecnico);
                ordens.add(ordem);

            }
        }
        return ordens;
    }


}
