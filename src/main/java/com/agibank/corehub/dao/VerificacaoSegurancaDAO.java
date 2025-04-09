package com.agibank.corehub.dao;

import com.agibank.corehub.controller.Alerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public double mediaValoresTransacaoMaioresQueMinimo(int id_conta_origem, double valorMinimo) throws SQLException {
        final String statusTransacao = "'APROVADA'";
        final String sql = """
                SELECT AVG(valor)
                FROM Transacao t Inner Join Status_Transacao st
                on t.id_transacao = st.id_transacao
                Where t.id_conta_origem = ? AND valor >= ? AND st.status =
                """ + statusTransacao;
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_conta_origem);
        stmt.setDouble(2, valorMinimo);
        rs = stmt.executeQuery();

        double valor = 0;
        if (rs.next()) {
            valor = rs.getDouble(1);
        }
        return valor;
    }

    public double maiorValorTransferenciaAprovada(int id_conta_origem) throws SQLException {
        final String statusTransacao = "'APROVADA'";
        final String sql = """
                SELECT MAX(valor)
                FROM Transacao t Inner Join Status_Transacao st
                on t.id_transacao = st.id_transacao
                Where t.id_conta_origem = ? AND st.status = 
                """ + statusTransacao;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_conta_origem);
        rs = stmt.executeQuery();
        double valor = 0;
        if (rs.next()) {
            valor = rs.getDouble(1);
        }
        return valor;
    }

    public Date transacaoCorreuHorarioSeguro(int id_transacao) throws SQLException {
        final String sql = """
                SELECT data
                FROM Status_Transacao
                WHERE id_transacao = ?
                """;
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_transacao);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDate("data");
            }
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro no acesso ao Banco", e.getMessage());
        } finally {
            fecharConexao();
        }
        return Date.from(Timestamp.valueOf("2022-01-01 00:00:00").toInstant());
    }
}
