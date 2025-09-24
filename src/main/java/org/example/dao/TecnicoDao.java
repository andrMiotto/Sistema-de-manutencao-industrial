package org.example.dao;

import org.example.model.Maquina;
import org.example.model.Tecnico;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDao {

    public Tecnico cadastrarTecnico(Tecnico tecnico) throws SQLException {
        String query = "INSERT INTO Tecnico(nome,especialidade) VALUES(?,?)";

        try (Connection connection = Conexao.conectar();

             PreparedStatement stmt = connection.prepareStatement(query)) {


            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialdiade());

            stmt.executeUpdate();
        }

        return tecnico;
    }


    public List<Tecnico> listarTodos() throws SQLException {
        String query = "SELECT id, nome,especialidade FROM Tecnico";

        List<Tecnico> tecnicos = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");

                Tecnico tecnico = new Tecnico(id, nome, especialidade);
                tecnicos.add(tecnico);
            }
        }

        return tecnicos;
    }

    public boolean verificaTecnicoDuplicado(Tecnico tecnico) throws SQLException {
        String query = "SELECT COUNT(0) AS linhas FROM Tecnico WHERE nome = ? AND especialidade = ? ";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialdiade());

            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("linhas") > 0) {
                return true;
            }
        }
        return false;
    }


}
