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
            documentoDAO.criarDocumento(documento);
            idUsuario = id_usuario;
            inserirTipoDocumento(numero);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void deletarDocumento(int id_documento){
        try{
            documentoDAO.deletarDocumento(id_documento);
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
            if (numero.length() == 11) {
                documentoDAO.determinarTipoDocumento("cpf", idUsuario);
            } else if (numero.length() == 8) {
                documentoDAO.determinarTipoDocumento("rg", idUsuario);
            } else if (numero.length() == 9) {
                documentoDAO.determinarTipoDocumento("cnh", idUsuario);
            } else if (numero.length() == 14) {
                documentoDAO.determinarTipoDocumento("cnpj", idUsuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarDocumento(String numero) {
    }
}
