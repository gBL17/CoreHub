package br.com.agibank.dao.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransacaoDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TransacaoDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public int cadastrarTransacao(Transacao transacao) throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public ResultSet buscarTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public int atualizarTransacao(Transacao transacao) throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public int excluirTransacao(Transacao transacao) throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }
}
