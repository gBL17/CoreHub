package com.agibank.corehub.dao;

import com.agibank.corehub.beans.transacao.StatusTransacao;
import com.agibank.corehub.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StatusTransacaoDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public StatusTransacaoDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int criarStatusTransacao(StatusTransacao statusTransacao) throws SQLException {
        final String sql = "INSERT INTO Status_Transacao (id_transacao, status, data) VALUES (?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, statusTransacao.getIdTransacao());
        stmt.setString(2, statusTransacao.getStatus());
        stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        return stmt.executeUpdate();
    }

    public StatusTransacao buscarStatusTransacao(int id) throws SQLException {
        StatusTransacao statusTransacao = new StatusTransacao();
        final String sql = "SELECT * FROM Status_Transacao WHERE id_transacao = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        if (rs.next()) {
            statusTransacao.setId(rs.getInt("id_status_transacao"));
            statusTransacao.setIdTransacao(rs.getInt("id_transacao"));
            statusTransacao.setStatus(rs.getString("status"));
            statusTransacao.setDataTransacao(rs.getTimestamp("data").toLocalDateTime());
        }
        return statusTransacao;
    }

    public int atualizarStatusTransacao(int id, String Status) throws SQLException {
        final String sql = "UPDATE Status_Transacao SET status = ?, data = ? WHERE id_transacao = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, Status);
        stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        stmt.setInt(3, id);
        return stmt.executeUpdate();
    }

    public int excluirStatusTransacao(int id) throws SQLException {
        final String sql = "DELETE FROM Status_Transacao WHERE id_transacao = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }
}
