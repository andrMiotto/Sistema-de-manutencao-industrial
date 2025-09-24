package org.example.dao;

import org.example.model.Peca;
import org.example.model.Tecnico;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PecaDao {


    public Peca cadastrarPeca(Peca peca) throws SQLException {
        String query = "INSERT INTO Peca (nome,estoque) VALUES (?,?)";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getEstoque());
            stmt.executeUpdate();
        }

        return peca;
    }


    public List<Peca> listarTodas() throws SQLException {
        String query = "SELECT id,nome,estoque FROM Peca";

        List<Peca> pecas = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query);) {
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double estoque = rs.getDouble("estoque");

                Peca peca = new Peca(id, nome, estoque);
                pecas.add(peca);
            }

        }
        return pecas;
    }

    public boolean verificaPecaDuplicada(Peca peca) throws SQLException {
        String query = "SELECT COUNT(0) AS linhas FROM Peca WHERE nome = ? AND estoque = ? ";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getEstoque());

            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("linhas") > 0) {
                return true;
            }
        }
        return false;
    }


}
