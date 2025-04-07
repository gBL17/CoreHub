package com.agibank.corehub.dao.conta;

import com.agibank.corehub.beans.conta.TipoConta;
import com.agibank.corehub.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoContaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TipoContaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarTipoConta(String tipoConta) throws SQLException {

        final String sql = "INSERT INTO TipoConta(tipo) VALUES(?)";

        stmt = con.prepareStatement(sql);
        stmt.setString(1, tipoConta);

        return stmt.executeUpdate();
    }

    public String buscarTipoConta(int id) throws SQLException {

        final String sql = "SELECT * FROM TipoConta WHERE id_tipo_conta = ?";

        TipoConta tipoConta = new TipoConta();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            tipoConta.setTipo(rs.getString("tipo"));

            return tipoConta.toString();

        }else return "Deu errado irmao";

    }

    public int atualizarClasse(String tipoConta) throws SQLException {

        final String sql = "UPDATE TipoConta SET tipo = ? WHERE id_tipo_conta = ?";

        stmt = con.prepareStatement(sql);

        stmt.setString(1, tipoConta);

        return stmt.executeUpdate();
    }

    public int deletarClasse(int id) throws SQLException {

        final String sql = "DELETE FROM TipoConta WHERE id_tipo_conta = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }
}
