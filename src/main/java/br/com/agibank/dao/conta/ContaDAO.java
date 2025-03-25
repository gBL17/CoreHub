package br.com.agibank.dao.conta;

import br.com.agibank.beans.conta.Conta;
import br.com.agibank.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarConta(int idUsuario, int idTipo, double idClasse, int idAgencia, int numero, double saldo, String dataAbertura, String status) throws SQLException {
        final String sql = "INSERT INTO Conta (id_usuario, id_tipo, id_classe, id_agencia, numero, saldo, data_abertura, status) VALUES (?,?,?,?,?,?,?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idTipo);
        stmt.setDouble(3, idClasse);
        stmt.setInt(4, idAgencia);
        stmt.setInt(5, numero);
        stmt.setDouble(6, saldo);
        stmt.setDate(7, java.sql.Date.valueOf(dataAbertura));
        stmt.setString(8, status);

        return stmt.executeUpdate();
    }

    public String buscarConta(int id) throws SQLException {
        final String sql = "SELECT * FROM Conta WHERE id_usuario = ?";

        Conta conta = new Conta();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            conta.setIdContaInterna(rs.getInt("id_conta_interna"));
            conta.setIdUsuario(rs.getInt("id_usuario"));
            conta.setIdTipo(rs.getInt("id_tipo"));
            conta.setIdClasse(rs.getDouble("id_classe"));
            conta.setIdAgencia(rs.getInt("id_agencia"));
            conta.setNumero(rs.getInt("numero"));
            conta.setSaldo(rs.getDouble("saldo"));
            conta.setDataAbertura(rs.getDate("data_abertura"));
            conta.setStatus(rs.getString("status"));

            return conta.toString();

        }else return "Deu errado";

    }

    public int buscarContaPorIdAgenciaENumeroConta(int idAgencia, int numeroConta) throws SQLException {
        final String sql = "SELECT * FROM Conta WHERE id_agencia = ? AND numero = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idAgencia);
        stmt.setInt(2, numeroConta);
        rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("id_conta");
        }
        return 0;
    }

    public int atualizarConta(int numero, Double saldo, String dataAbertura, String status) throws SQLException {
        final String sql = "UPDATE Conta SET numero = ?, saldo = ?, data_abertura = ?, status = ?  WHERE id_conta_interna = ?";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, numero);
        stmt.setDouble(2, saldo);
        stmt.setDate(3, java.sql.Date.valueOf(dataAbertura));
        stmt.setString(4, status);

        return stmt.executeUpdate();

    }

    public int deletarConta(int id) throws SQLException {
        final String sql = "DELETE FROM Conta(id_conta_interna) VALUES(?)";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }
}