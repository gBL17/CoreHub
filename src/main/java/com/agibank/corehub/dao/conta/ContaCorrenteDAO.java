package com.agibank.corehub.dao.conta;

import com.agibank.corehub.beans.conta.ContaCorrente;
import com.agibank.corehub.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaCorrenteDAO {


    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaCorrenteDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarContaCorrente(int idConta, int limiteCredito) throws SQLException {

        final String sql = "INSERT INTO Conta_Corrente(id_conta, limite_credito) VALUES(?, ?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idConta);
        stmt.setInt(2, limiteCredito);

        return stmt.executeUpdate();
    }

    public String buscarContaCorrente(int id) throws SQLException {

        final String sql = "SELECT * FROM Conta_Corrente WHERE id_conta_corrente = ?";

        ContaCorrente contaCorrente = new ContaCorrente();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            contaCorrente.setIdContaCorrente(rs.getInt("id_conta_corrente"));
            contaCorrente.setIdConta(rs.getInt("id_conta"));
            contaCorrente.setLimiteCredito(rs.getInt("limite_credito"));

            return contaCorrente.toString();

        }else return "Deu errado irmao";

    }

    public int atualizarContaCorrente(int limiteCredito, int idConta, int idContaCorrente) throws SQLException {

        final String sql = "UPDATE Beneficio SET limite_credito = ?, id_conta = ? WHERE id_conta_corrente = ?";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, limiteCredito);
        stmt.setInt(2, idConta);
        stmt.setInt(3, idContaCorrente);

        return stmt.executeUpdate();
    }

    public int deletarContaCorrente(int id) throws SQLException {

        final String sql = "DELETE FROM Conta_Corrente WHERE id_conta_corrente = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }

    public int descontarSaldoContaCorrente(int idConta) throws SQLException {

        final String sql = """
                UPDATE Conta
                JOIN Conta_Corrente cc ON c.id_conta = cc.id_conta
                JOIN Tipo_Conta tc ON c.id_tipo = tc.id_tipo_conta
                SET saldo = saldo - 68.40
                WHERE c.id_conta = ?
                AND c.id_tipo = 1;
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idConta);

        return stmt.executeUpdate();
    }
}
