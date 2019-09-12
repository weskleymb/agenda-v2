package br.senac.rn.agenda.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AgendaDataBase {

    private final String DRIVER = "org.mariadb.jdbc.Driver";
    private final String DATABASE = "db_agenda_v2";
    private final String USER = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mariadb://localhost:3306/" + DATABASE;

    private Connection connection = null;

    public Boolean openConnection() {
        try {
            Class.forName(DRIVER); //busca o Driver de conexao
            connection = DriverManager.getConnection(URL, USER, PASSWORD); //busca uma conexao valida
            return true;
        } catch (ClassNotFoundException | SQLException error) {
            System.out.println("Erro: " + error);
        }
        return false;
    }

    public Boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException error) {
            System.out.println("Erro: " + error);
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

}
