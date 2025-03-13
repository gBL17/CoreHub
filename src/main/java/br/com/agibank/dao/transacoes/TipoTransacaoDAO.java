package br.com.agibank.dao.transacoes;

import br.com.agibank.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoTransacaoDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TipoTransacaoDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarTipoTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public ResultSet buscarTipoTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public int atualizarTipoTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public int excluirTipoTransacao() throws SQLException {
        final String sql = "";
        stmt = con.prepareStatement(sql);
        return stmt.executeUpdate();
    }

}
