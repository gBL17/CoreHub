package br.com.agibank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
