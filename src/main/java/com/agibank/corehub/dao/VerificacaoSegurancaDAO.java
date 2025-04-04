package com.agibank.corehub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerificacaoSegurancaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public VerificacaoSegurancaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public double mediaValores(int id_conta_origem) throws SQLException {
        final String sql = "SELECT AVG(valor) FROM Transacao WHERE id_conta_origem = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_conta_origem);
        rs = stmt.executeQuery();

        double valor = 0;
        if (rs.next()) {
            valor = rs.getDouble(1);
        }
        return valor;
    }

    public double maiorValor(int id_conta_origem) throws SQLException {
        final String sql = "SELECT MAX(valor) FROM Transacao WHERE id_conta_origem = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_conta_origem);
        rs = stmt.executeQuery();

        double valor = 0;
        if (rs.next()) {
            valor = rs.getDouble(1);
        }
        return valor;
    }

    public Date horarioTransacao(int id_transacao) throws SQLException {
        final String sql = "SELECT data FROM Status_Transacao WHERE id_transacao = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_transacao);
        rs = stmt.executeQuery();

        Date valor = null;
        if (rs.next()) {
            Date timestamp = rs.getDate(3);

            // Converte o timestamp em um objeto Date
            valor = new Date(String.valueOf(timestamp));
        }
        return valor;
    }
}
