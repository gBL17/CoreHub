package com.agibank.corehub.dao.conta;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.beans.conta.TipoConta;
import br.com.agibank.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean verificarContaExiste(int idTipo, int idAgencia, int numero) throws SQLException {

        String sql = "SELECT COUNT(*) FROM Conta WHERE id_tipo = ? AND id_agencia = ? AND numero = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idTipo);
        stmt.setInt(2, idAgencia);
        stmt.setInt(3, numero);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Se retornar um número maior que 0, significa que essa conta já existe
        }

        return false;


    }

    public int cadastrarConta(int idUsuario, int idTipo, double idClasse, int idAgencia, int numero, double saldo, String dataAbertura) throws SQLException {
        final String sql = "INSERT INTO Conta (id_usuario, id_tipo, id_classe, id_agencia, numero, saldo, data_abertura, status, score) VALUES (?,?,?,?,?,?,?,?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idTipo);
        stmt.setDouble(3, idClasse);
        stmt.setInt(4, idAgencia);
        stmt.setInt(5, numero);
        stmt.setDouble(6, saldo);
        stmt.setDate(7, java.sql.Date.valueOf(dataAbertura));
        stmt.setString(8, "Aguardando resposta");
        stmt.setInt(8, 0);

        return stmt.executeUpdate();
    }

    public Conta buscarConta(int id) throws SQLException {
        final String sql = "SELECT * FROM Conta WHERE id_conta = ?";

        Conta conta = new Conta();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {

            conta.setIdConta(rs.getInt("id_conta"));
            conta.setIdUsuario(rs.getInt("id_usuario"));
            conta.setIdTipo(rs.getInt("id_tipo"));
            conta.setIdClasse(rs.getDouble("id_classe"));
            conta.setIdAgencia(rs.getInt("id_agencia"));
            conta.setNumero(rs.getInt("numero"));
            conta.setSaldo(rs.getDouble("saldo"));
            conta.setDataAbertura(rs.getDate("data_abertura"));
            conta.setStatus(rs.getString("status"));

            return conta;

        } else return null;

    }

    public int atualizarConta(int numero, Double saldo, String dataAbertura, String status) throws SQLException {
        final String sql = "UPDATE Conta SET numero = ?, saldo = ?, data_abertura = ?, status = ?  WHERE id_conta = ?";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, numero);
        stmt.setDouble(2, saldo);
        stmt.setDate(3, java.sql.Date.valueOf(dataAbertura));
        stmt.setString(4, status);

        return stmt.executeUpdate();

    }

    public double atualizarSaldo(int id_conta, double valor) throws SQLException {
        final String sql = "UPDATE Conta set saldo = saldo + ? where id_conta = ?";

        stmt = con.prepareStatement(sql);

        stmt.setDouble(1, valor);
        stmt.setInt(2, id_conta);

        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("saldo");
        }

        return 0;
    }

    public int deletarConta(int id) throws SQLException {
        final String sql = "DELETE FROM Conta(id_conta) VALUES(?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }

    public String buscarStatusConta(int id) throws SQLException {

        final String sql = "SELECT status FROM Conta WHERE id_conta = ?";

        Conta conta = new Conta();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {

            return rs.getString("status");

        } else return "Deu errado";
    }

    public String exibirTitularConta(int id) throws SQLException {
        Usuario usuario = new Usuario();

        final String sql = """
                SELECT Usuario.nome
                FROM Conta
                JOIN Usuario ON Conta.id_usuario = Usuario.id_usuario
                WHERE Conta.id_conta = ?;
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("nome");
        } else return "titular não encontrado";

    }

    public String exibirTiposConta(int id) throws SQLException {

        ArrayList<TipoConta> tiposConta = new ArrayList<>();
        TipoConta tipoConta = new TipoConta();

        final String sql = """
                SELECT *
                FROM Conta
                WHERE Conta.id_conta = ?;
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {

            tipoConta.setTipo(rs.getString("tipo"));
            tiposConta.add(tipoConta);
        }

        return tiposConta.toString();

    }

    public ArrayList<Conta> listarContasUsuario(int idUsuario) throws SQLException {
        ArrayList<Conta> contas = new ArrayList<>();

        String sql = """
                SELECT c.id_conta, tc.tipo, cl.tipo_classe, a.numero_agencia, c.numero, c.saldo, c.data_abertura, c.status, c.score
                FROM Conta c
                INNER JOIN Tipo_Conta tc ON c.id_tipo = tc.id_tipo_conta
                INNER JOIN Classe cl ON c.id_classe = cl.id_classe
                INNER JOIN Agencia a ON c.id_agencia = a.id_agencia
                WHERE c.id_usuario = ?
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Conta conta = new Conta();

            conta.setIdConta(rs.getInt("id_conta"));
            conta.setTipo(rs.getString("tipo"));
            conta.setTipoClasse(rs.getString("tipo_classe"));
            conta.setNumeroAgencia(rs.getInt("numero_agencia"));
            conta.setNumero(rs.getInt("numero"));
            conta.setSaldo(rs.getDouble("saldo"));
            conta.setDataAbertura(rs.getDate("data_abertura"));
            conta.setStatus(rs.getString("status"));
            conta.setScore(rs.getInt("score"));

            contas.add(conta);

        }
        return contas;
    }


    public int retornarIdConta(int numeroAgencia, int numeroConta) throws SQLException{
        final String sql = """
                SELECT c.id_conta
                FROM Conta c
                Inner join Agencia a ON a.id_agencia = c.id_agencia
                Where a.numero = ?
                AND c.numero = ?
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, numeroAgencia);
        stmt.setInt(2, numeroConta);
        rs = stmt.executeQuery();

        if(rs.next()){

            return rs.getInt("id_conta");

        }else{return 0;}

    }
}

