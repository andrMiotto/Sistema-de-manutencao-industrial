package org.example.dao;

import org.example.model.Peca;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PecaDao {

    public Peca cadastrarPeca(Peca peca) throws SQLException {
        String query = "INSERT INTO Peca (nome,estoque) VALUES (?,?)";

        try(Connection connection = Conexao.conectar();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, peca.getNome());
            stmt.setDouble(2,peca.getEstoque());
            stmt.executeUpdate();
        }

        return peca;
    }


}
