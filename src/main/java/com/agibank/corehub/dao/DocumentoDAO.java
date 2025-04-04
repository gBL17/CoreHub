package com.agibank.corehub.dao;

import com.agibank.corehub.beans.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public DocumentoDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int criarDocumento(Documento documento) throws SQLException {
        final String sql = "INSERT INTO Documento (numero, id_usuario, tipo, arquivo) VALUES ( ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, documento.getNumero());
        stmt.setInt(2, documento.getId_usuario());
        stmt.setString(3, documento.getTipo());
        stmt.setString(4, documento.getArquivo());
        return stmt.executeUpdate();
    }

    public void deletarDocumento(int id_documento) throws SQLException {
        final String sql = "DELETE FROM Documento WHERE id_documento = ?";
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, id_documento);
        stmt.executeUpdate();
    }

    public int atualizarDocumento(Documento documento) throws SQLException {
        final String sql = "UPDATE Documento Set numero = ?, id_usuario = ?, tipo = ?, arquivo = ?";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, documento.getNumero());
        stmt.setInt(2, documento.getId_usuario());
        stmt.setString(3, documento.getTipo());
        stmt.setString(4, documento.getArquivo());
        return stmt.executeUpdate();
    }

    public void determinarTipoDocumento(String tipo, int id_usuario) throws SQLException {
        final  String sql = "UPDATE Documento SET tipo = ? WHERE id_usuario = ?";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, tipo);
        stmt.setInt(2, id_usuario);
        stmt.executeUpdate();
    }

    public static void atualizarDocumento(int idUsuario, String tipo, String numero, String arquivo) {

    }
}
