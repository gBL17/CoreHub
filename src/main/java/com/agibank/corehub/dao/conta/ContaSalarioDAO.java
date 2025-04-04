package com.agibank.corehub.dao.conta;

import com.agibank.corehub.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaSalarioDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaSalarioDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarContaPoupanca(int idConta, int cnpj, String dataPagamento, double valorPagamento) throws SQLException {

        final String sql = "INSERT INTO Conta_Salario(id_conta, cnpj, data_pagamento, valor_pagamento) VALUES(?, ?, ?, ?)";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, idConta);
        stmt.setInt(2, cnpj);
        stmt.setDate(3, java.sql.Date.valueOf(dataPagamento));
        stmt.setDouble(4, valorPagamento);

        return stmt.executeUpdate();
    }

    public String buscarContaSalario(int id) throws SQLException {

        final String sql = "SELECT * FROM Conta_Salario WHERE id_conta_salario = ?";

        br.com.agibank.beans.conta.ContaSalario contaSalario = new br.com.agibank.beans.conta.ContaSalario();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            contaSalario.setIdContaSalario(rs.getInt("id_conta_salario"));
            contaSalario.setIdConta(rs.getInt("id_conta"));
            contaSalario.setCnpj(rs.getInt("cnpj"));
            contaSalario.setDataPagamento(rs.getDate("data_pagamento"));
            contaSalario.setValorPagamento(rs.getDouble("valor_pagamento"));

            return contaSalario.toString();

        }else return "Deu errado irmao";

    }

    public int atualizarContaSalario(int idConta, int cnpj, String dataPagamento, double valorPagamento, int idContaSalario) throws SQLException {

        final String sql = "UPDATE Conta_Salario SET id_conta = ?, cnpj = ?, data_pagamento = ?, valor_pagamento = ? WHERE id_conta_salario = ?";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, idConta);
        stmt.setInt(2, cnpj);
        stmt.setDate(3, java.sql.Date.valueOf(dataPagamento));
        stmt.setDouble(4, valorPagamento);
        stmt.setInt(5, idContaSalario);

        return stmt.executeUpdate();
    }

    public int deletarContaSalario(int id) throws SQLException {

        final String sql = "DELETE FROM Conta_Salario WHERE id_conta_salario = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }

    public int pegarCnpjContaSalario(int idConta) throws SQLException{

        br.com.agibank.beans.conta.ContaSalario contaSalario = new br.com.agibank.beans.conta.ContaSalario();

        final String sql = """
                Select cs.cnpj
                FROM Conta c
                Inner join Conta_Salario cs ON cs.id_conta = c.id_conta
                Where c.id_conta = ?
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idConta);
        rs = stmt.executeQuery();

        if(rs.next()){
            return rs.getInt("cnpj");
        }else{return 0;}


    }

    public String pegarCnpjDocumento(int idConta) throws SQLException{

        final String sql = """
                SELECT d.numero
                FROM Usuario u
                Inner Join Conta c ON c.id_usuario = u.id_usuario
                Inner join Documento d ON d.id_usuario = u.id_usuario
                WHERE c.id_conta = ?
                AND d.tipo = 'cnpj'
                """;

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idConta);
        rs = stmt.executeQuery();

        if(rs.next()){
            return rs.getString("numero");
        }else{return "não";}
    }


}
