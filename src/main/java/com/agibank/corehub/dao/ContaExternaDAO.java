package com.agibank.corehub.dao;

import com.agibank.corehub.beans.conta.ContaExterna;

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

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarContaExterna(int agencia, int numeroContaExterna, int codigoBanco) throws SQLException {

        final String sql = "INSERT INTO Conta_Externa(agencia, dados_conta_externa, codigo_banco) VALUES(?,?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, codigoBanco);

        return stmt.executeUpdate();
    }

    public String buscarContaExterna(int id) throws SQLException {

        final String sql = "SELECT * FROM Conta_Externa WHERE id_conta_externa = ?";

        ContaExterna contaExterna = new ContaExterna();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){
            contaExterna.setIdContaExterna(rs.getInt("id_conta_externa"));
            contaExterna.setAgencia(rs.getInt("agencia"));
            contaExterna.setAgencia(rs.getInt("numero_conta_externa"));
            contaExterna.setAgencia(rs.getInt("codigo_banco"));
            return contaExterna.toString();
        }else return "Deu errado irmao";
    }

    public int buscarIdContaExterna(int agencia, int numeroConta, int codigoBanco) throws SQLException {
        final String sql = "SELECT * FROM Conta_Externa WHERE agencia = ? AND numero_conta_externa=? AND codigo_banco_externo=?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroConta);
        stmt.setInt(3, codigoBanco);
        rs = stmt.executeQuery();

        if(rs.next()){
            return rs.getInt("id_conta_externa");
        }else return 0;
    }

    public int atualizarContaExterna(int idContaExterna, int agencia, int numeroContaExterna, int codigoBanco) throws SQLException {
        final String sql = "UPDATE Conta_Salario SET dados_conta_externa = ? WHERE id_conta_externa = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, codigoBanco);
        stmt.setInt(4, idContaExterna);

        return stmt.executeUpdate();
    }

    public int deletarContaExterna(int id) throws SQLException {
        final String sql = "DELETE FROM Conta_Externa WHERE id_conta_externa = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }

}