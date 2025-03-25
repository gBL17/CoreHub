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

    public int criarContaExterna(int agencia, int numeroContaExterna, int codigoBancoExterno) throws SQLException {
        final String sql = "INSERT INTO Conta_Externa (agencia, numero_conta_externa, codigo_banco) VALUES (?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, codigoBancoExterno);
        return stmt.executeUpdate();
    }

    public ContaExterna buscarContaExterna(int id) throws SQLException {
        ContaExterna contaExterna =  new ContaExterna();
        final String sql = "SELECT * FROM Conta_Externa WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            contaExterna.setIdContaExterna(rs.getInt("id_conta_externa"));
            contaExterna.setAgencia(rs.getInt("agencia"));
            contaExterna.setNumeroContaExterna(rs.getInt("numero_conta_externa"));
            contaExterna.setCodigoBancoExterno(rs.getInt("codigo_banco"));
        }
        return contaExterna;
    }

    public int buscarIdContaExterna(int agencia, int numeroContaExterna, int codigoBanco) throws SQLException {
        final String sql = "SELECT id_conta_externa FROM Conta_Externa WHERE agencia = ? AND numero_conta_externa = ? AND codigo_banco = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, codigoBanco);
        rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getInt("id_conta_externa");
        }
        return 0;
    }

    public int atualizarContaExterna(int id, int agencia, int numeroConta, int codigoBancoExterno) throws SQLException {
        final String sql = "UPDATE Conta_Externa SET agencia = ?, numero_conta_externa = ?, codigo_banco = ? WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroConta);
        stmt.setInt(3, codigoBancoExterno);
        stmt.setInt(4, id);
        return stmt.executeUpdate();
    }

    public int deletarContaExterna(int id) throws SQLException {
        final String sql = "DELETE FROM Conta_Externa WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }
}
