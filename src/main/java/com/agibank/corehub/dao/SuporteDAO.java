package com.agibank.corehub.dao;

import com.agibank.corehub.beans.Suporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SuporteDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;


    public SuporteDAO() throws SQLException {
        con = Conexao.getConexao();
    }


    public void fecharConexao() throws SQLException {
        con.close();
    }


    public void inserirSuporte(int id, String descricao) throws SQLException{
        String sql = "INSERT INTO Suporte (id_usuario,descricao,data_abertura) VALUES (?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.setString(2, descricao);
        stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        stmt.executeUpdate();
        System.out.println("Chamado cadastrado com sucesso");
    }

    public Suporte buscarChamadoPorId(int id_suporte) throws SQLException{
        String sql = "SELECT id_usuario, descricao, responsavel, resolucao FROM Suporte WHERE id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_suporte);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Suporte suporte = new Suporte();
            suporte.setidCliente(rs.getInt("id_usuario"));
            suporte.setDescricao(rs.getString("descricao"));
            suporte.setidFuncionario(rs.getInt("responsavel"));
            suporte.setResolucao(rs.getString("resolucao"));
            return suporte;
        }

        return null;
    }

    public ArrayList<Suporte> buscarChamadoPorIdUsuario(int id_usuario) throws SQLException{
        String sql = "SELECT descricao, resolucao FROM Suporte WHERE id_usuario = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_usuario);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Suporte> suportes = new ArrayList<>();

        while (rs.next()) {
            Suporte suporte = new Suporte();
            suporte.setDescricao(rs.getString("descricao"));
            suporte.setResolucao(rs.getString("resolucao"));
            suportes.add(suporte);
        }

        return suportes;
    }

    public void listarChamados() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getString("Usuario.nome") + " ");
            System.out.print(rs.getString("descricao") + " ");
            System.out.print(rs.getInt("responsavel") + " ");
            System.out.print(rs.getString("resolucao") + "\n");
        }

    }

    public void listarChamadosAtendidos() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario WHERE responsavel IS NOT NULL";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id_usuario") + " ");
            System.out.print(rs.getString("descricao") + " ");
            System.out.print(rs.getInt("responsavel") + " ");
            System.out.print(rs.getString("resolucao") + "\n");
        }
    }

    public void listarChamadosPendentes() throws SQLException{
        String sql = "SELECT * FROM Suporte INNER JOIN Usuario ON Usuario.id_usuario = Suporte.id_usuario WHERE responsavel IS NULL";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id_suporte") + " ");
            System.out.print(rs.getString("descricao") + "\n");
        }

    }

    public void inserirAtendenteSuporte(int id_suporte, int id_funcionario) throws SQLException{
        String sql = "UPDATE Suporte SET responsavel = ? WHERE id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_funcionario);
        stmt.setInt(2, id_suporte);
        stmt.executeUpdate();
    }

    public void resolverChamado(int id_suporte, String resolucao) throws SQLException{
        String sql = "UPDATE Suporte SET resolucao = ?, data_fechamento = ? WHERE id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, resolucao);
        stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        stmt.setInt(3, id_suporte);

        stmt.executeUpdate();
    }

    public Suporte verificarAtendenteChamado(int id_chamado, int id_usuario) throws SQLException{
        String sql = "SELECT descricao FROM Suporte WHERE responsavel = ? and id_suporte = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_usuario);
        stmt.setInt(2, id_chamado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Suporte suporte = new Suporte();
            suporte.setDescricao(rs.getString("descricao"));
            return suporte;
        }

        return null;
    }
}
