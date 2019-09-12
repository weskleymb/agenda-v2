package br.senac.rn.agenda.repository;

import br.senac.rn.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {

    private final String DRIVER = "org.mariadb.jdbc.Driver";
    private final String DATABASE = "db_agenda_v2";
    private final String USER = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mariadb://localhost:3306/" + DATABASE;

    private Connection connection = null;

    public void conecta() {
        try {
            Class.forName(DRIVER); //busca o Driver de conexao
            connection = DriverManager.getConnection(URL, USER, PASSWORD); //busca uma conexao valida
        } catch (ClassNotFoundException error) {
            System.out.println("Erro: " + error);
        } catch (SQLException error) {
            System.out.println("Erro: " + error);
        }
    }

    public List<Contato> buscaTodos() {
        conecta();
        List<Contato> contatos = new ArrayList();
        String sql = "SELECT * FROM tb_contatos";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();

                contato.setId(rs.getLong("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));

                contatos.add(contato);
            }
            return contatos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
