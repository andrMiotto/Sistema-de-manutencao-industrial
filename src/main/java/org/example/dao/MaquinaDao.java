package org.example.dao;

import org.example.model.Maquina;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDao {


    public Maquina cadastrarMaquina(Maquina maquina) throws SQLException {
        String query = "INSERT INTO Maquina (nome,setor,status) values (?,?,?)";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, maquina.getNome());
            stmt.setString(2, maquina.getSetor());
            stmt.setString(3, maquina.getStatus().name());

            stmt.executeUpdate();

        }

        return maquina;
    }


    public List<Maquina> listarTodas() throws SQLException {
        String query = "SELECT id,nome,setor FROM Maquina WHERE status = 'OPERACIONAL';";

        List<Maquina> maquinas = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String setor = rs.getString("setor");

                Maquina maquina = new Maquina(id, nome, setor);
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }

}
