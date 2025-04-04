package com.agibank.corehub.dao;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.controller.CifradorSenha;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import com.agibank.corehub.dao.Conexao;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public UsuarioDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int criarUsuario(Usuario usuario) throws SQLException {
        final String sql = "INSERT INTO Usuario (nome, apelido, senha, email, telefone, data_nascimento, rua, numero, complemento) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);

        CifradorSenha cifradorSenha = new CifradorSenha();

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getApelido());
        stmt.setString(3, cifradorSenha.cifrarSenha(usuario.getSenha()));
        stmt.setString(4, usuario.getEmail());
        stmt.setInt(5, usuario.getTelefone());
        stmt.setDate(6, Date.valueOf(usuario.getData_nascimento()));
        stmt.setString(7, usuario.getRua());
        stmt.setInt(8, usuario.getNumero());
        stmt.setString(9, usuario.getComplemento());
        return stmt.executeUpdate();
    }

    public int atualizarCadastroUsuario(String tabela, String dado, int id_usuario) throws SQLException {
        final String sql = "UPDATE Usuario SET " + tabela + " = ? where id_usuario = ?";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, dado);
        stmt.setInt(2, id_usuario);
        return stmt.executeUpdate();
    }

    public int atualizarCadastroUsuario(String tabela, int dadoNumerico, int id_usuario) throws SQLException {
        final String sql = "UPDATE Usuario SET " + tabela + " = ? where id_usuario = ?";
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, dadoNumerico);
        stmt.setInt(2, id_usuario);
        return stmt.executeUpdate();
    }

    public void deletarUsuario(int id_usuario) throws SQLException {
        final String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_usuario);
        stmt.executeUpdate();
    }

    public int verificarTipoUsuario(int id_usuario) throws SQLException{
        int id = 0;
        String sql = "SELECT * FROM Funcionario WHERE id_usuario = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id_usuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            id = rs.getInt(id_usuario);
        }

        return id;
    }


    //TODO adicionar mais dados
    public Usuario buscarUsuarioPorId(int  id_usuario) throws SQLException{
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_usuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setApelido(rs.getString("apelido"));
            return usuario;
        }

        return null;
    }

    public Usuario buscarUsuarioApelido(String apelido) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE apelido = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, apelido);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setFuncionario(verificarTipoUsuario(rs.getInt("id_usuario")) != 0);

            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setApelido(rs.getString("apelido"));
            return usuario;
        }

        return null;
    }
}