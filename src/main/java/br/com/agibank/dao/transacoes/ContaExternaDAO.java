package br.com.agibank.dao.transacoes;

import br.com.agibank.beans.conta.ContaExterna;
import br.com.agibank.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaExternaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaExternaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public int criarContaExterna(int agencia, int numeroContaExterna) throws SQLException {
        final String sql = "INSERT INTO Conta_Externa (agencia, numero_conta_externa) VALUES (?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        return stmt.executeUpdate();
    }

    public ContaExterna consultarContaExterna(int id) throws SQLException {
        ContaExterna contaExterna =  new ContaExterna();
        final String sql = "SELECT * FROM Conta_Externa WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            contaExterna.setIdContaExterna(rs.getInt("id_conta_externa"));
            contaExterna.setAgencia(rs.getInt("agencia"));
            contaExterna.setNumeroContaExterna(rs.getInt("numero_conta_externa"));
        }
        return contaExterna;
    }

    public int atualizarContaExterna(int id, int agencia, int numeroConta) throws SQLException {
        final String sql = "UPDATE Conta_Externa SET agencia = ?, numero_conta_externa = ? WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, agencia);
        stmt.setInt(3, numeroConta);
        return stmt.executeUpdate();
    }

    public int deletarContaExterna(int id) throws SQLException {
        final String sql = "DELETE FROM Conta_Externa WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }
}
