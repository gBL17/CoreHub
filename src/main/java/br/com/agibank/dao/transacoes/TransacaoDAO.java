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

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarTransacao(Transacao transacao) throws SQLException {
        final String sql = "INSERT INTO Transacao (id_tipo_transacao, id_conta, origem, destino, valor, descricao) VALUES (?,?,?,?,?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, transacao.getTipo());
        stmt.setInt(2, transacao.getIdConta());
        stmt.setInt(3, transacao.getContaOrigem());
        stmt.setInt(4, transacao.getContaDestino());
        stmt.setDouble(5, transacao.getValor());
        stmt.setString(6, transacao.getDescricao());
        return stmt.executeUpdate();
    }

    public String buscarTransacaoPorId(int id) throws SQLException {
        final String sql = "SELECT * FROM Transacao WHERE id_transacao = ?";
        Transacao transacao = new Transacao();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs != null) {
            transacao.setId(rs.getInt("id_transacao"));
            transacao.setTipo(rs.getInt("id_tipo_transacao"));
            transacao.setIdConta(rs.getInt("id_conta"));
            transacao.setContaOrigem(rs.getInt("origem"));
            transacao.setContaDestino(rs.getInt("destino"));
            transacao.setValor(rs.getDouble("valor"));
            transacao.setDescricao(rs.getString("descricao"));

            return transacao.toString();
        } else return "Transação não Encontrada";
    }
}
