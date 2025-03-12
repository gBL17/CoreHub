package br.com.agibank.dao.transacoes;

import br.com.agibank.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusTransacaoDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public StatusTransacaoDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public int cadastrarStatusTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public ResultSet buscarStatusTransacao() throws  SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public int atualizarStatusTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public int excluirStatusTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }
}
