package com.agibank.corehub.dao;

import com.agibank.corehub.beans.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenciaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;


    public AgenciaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public void inserirAgencia(String rua, int numero, String complemento, String cidade) throws SQLException{
        String sql = "INSERT INTO Agencia (rua,numero,complemento) VALUES (?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, rua);
        stmt.setInt(2, numero);
        stmt.setString(3, complemento);
        stmt.setString(4, cidade);
        stmt.executeUpdate();
        System.out.println("Agencia inserida com sucesso");

    }

    public void deletarAgencia(int id_agencia) throws SQLException{
        String sql = "DELETE FROM Agencia WHERE id_agencia = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_agencia);
        stmt.executeUpdate();
        System.out.println("Teste realizado com sucesso");
    }

    public void alterarEndereco(int id_agencia, String rua, int numero, String complemento, String cidade) throws SQLException{
        String sql = "UPDATE Agencia SET rua = ?, numero = ?, complemento = ?, cidade = ? WHERE id_agencia = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, rua);
        stmt.setInt(2, numero);
        stmt.setString(3, complemento);
        stmt.setString(4, cidade);
        stmt.setInt(5,id_agencia);
        stmt.executeUpdate();
        System.out.println("Endereço atualizado com sucesso");
    }

    public void listarAgencias() throws SQLException{
        String sql = "SELECT * FROM Agencia";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id_agencia") + " ");
            System.out.print(rs.getString("rua") + " ");
            System.out.print(rs.getString("numero") + " ");
            System.out.print(rs.getString("complemento") + " ");
            System.out.println(rs.getString("cidade") + "\n");
        }
    }

    public Agencia buscarAgenciaPorId(int id_agencia) throws SQLException{
        String sql = "SELECT * FROM Agencia WHERE id_agencia = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_agencia);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Agencia agencia = new Agencia();
            agencia.setComplemento(rs.getString("complemento"));
            agencia.setNumero(rs.getInt("numero"));
            agencia.setRua(rs.getString("rua"));
            agencia.setCidade(rs.getString("cidade"));
            return agencia;
        }

        return null;
    }
}
