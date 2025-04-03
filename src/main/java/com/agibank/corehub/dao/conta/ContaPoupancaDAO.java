package com.agibank.corehub.dao.conta;

import com.agibank.corehub.beans.conta.ContaPoupanca;
import com.agibank.corehub.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaPoupancaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaPoupancaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarContaPoupanca(double limiteTransacao, double rendimento, int idConta) throws SQLException {

        final String sql = "INSERT INTO Conta_Poupanca(limite_transacao, rendimento, id_conta) VALUES(?, ?, ?)";

        stmt = con.prepareStatement(sql);
        stmt.setDouble(1, limiteTransacao);
        stmt.setDouble(2, rendimento);
        stmt.setInt(3, idConta);

        return stmt.executeUpdate();
    }

    public String buscarContaPoupanca(int id) throws SQLException {

        final String sql = "SELECT * FROM Conta_Poupanca WHERE id_conta_poupanca = ?";

        ContaPoupanca contaPoupanca = new ContaPoupanca();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            contaPoupanca.setIdPoupanca(rs.getInt("id_conta_poupanca"));
            contaPoupanca.setLimiteTrasacao(rs.getDouble("limite_transacao"));
            contaPoupanca.setRendimento(rs.getDouble("rendimento"));
            contaPoupanca.setIdConta(rs.getInt("id_conta"));

            return contaPoupanca.toString();

        }else return "Deu errado irmao";

    }

    public int atualizarContaPoupanca(double limiteTransacao, double rendimento, int idConta, int idContaPoupanca) throws SQLException {

        final String sql = "UPDATE Conta_Poupanca SET limite_transacao = ?, rendimento = ?, id_conta = ? WHERE id_conta_poupanca = ?";

        stmt = con.prepareStatement(sql);

        stmt.setDouble(1, limiteTransacao);
        stmt.setDouble(2, rendimento);
        stmt.setInt(3, idConta);
        stmt.setInt(4, idContaPoupanca);

        return stmt.executeUpdate();
    }

    public int deletarContaPoupanca(int id) throws SQLException {

        final String sql = "DELETE FROM Conta_Poupanca WHERE id_conta_poupanca = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }

}
