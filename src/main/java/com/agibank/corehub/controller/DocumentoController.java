package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Documento;
import com.agibank.corehub.dao.DocumentoDAO;

import java.sql.SQLException;

public class DocumentoController {
    private DocumentoDAO documentoDAO = new DocumentoDAO();
    int idUsuario = 0;
    public DocumentoController() throws SQLException {
    }

    public int adicionarDocumento(int id_usuario, String tipo, String numero, String arquivo) throws SQLException {

        try {
            Documento documento = new Documento(1, id_usuario, tipo, numero, arquivo);
            DocumentoDAO.criarDocumento(documento);
            idUsuario = id_usuario;
            inserirTipoDocumento(numero);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void deletarDocumento(int id_documento){
        try{
            DocumentoDAO.deletarDocumento(id_documento);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int alterarDocumento(int id_usuario, String tipo, String numero, String arquivo) throws SQLException {
        DocumentoDAO.atualizarDocumento(id_usuario, tipo, numero, arquivo);
        inserirTipoDocumento(numero);
        return id_usuario;
    }

    public void inserirTipoDocumento(String numero) {
        try {

            boolean documentoValido = false;
            while (!documentoValido) {
                if (numero.length() == 11) {
                    DocumentoDAO.determinarTipoDocumento("cpf", idUsuario);
                    documentoValido = true;
                } else if (numero.length() == 8) {
                    DocumentoDAO.determinarTipoDocumento("rg", idUsuario);
                    documentoValido = true;
                } else if (numero.length() == 9) {
                    DocumentoDAO.determinarTipoDocumento("cnh", idUsuario);
                    documentoValido = true;
                } else if (numero.length() == 14) {
                    DocumentoDAO.determinarTipoDocumento("cnpj", idUsuario);
                    documentoValido = true;
                } else {
                    System.out.println("Documento não aceito no cadastro, tente novamente.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarDocumento(String numero) {
    }
}
