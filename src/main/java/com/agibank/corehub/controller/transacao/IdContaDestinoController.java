package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.dao.ContaExternaDAO;
import com.agibank.corehub.dao.conta.ContaDAO;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IdContaDestinoController {
    DestinatarioTransacao destinatario;

    public IdContaDestinoController(DestinatarioTransacao destinatario) {
        this.destinatario = destinatario;
    }

    public int buscarIdContaDestino(DestinatarioTransacao destinatario) throws SQLException {
        int idContaDestino;

        if (destinatario.getCodigoBanco() != 121){
            idContaDestino = buscarIdContaExterna(destinatario);

            if (idContaDestino == 0){
                cadastrarContaExterna(destinatario);
                idContaDestino = buscarIdContaDestino(destinatario);
            }

        } else {
            idContaDestino = buscarIdContaInterna(destinatario);
        }
        return idContaDestino;
    }

    public int buscarIdContaExterna(DestinatarioTransacao destinatario) throws SQLException {
        int idContaExterna;
        ContaExternaDAO contaExternaDAO = new ContaExternaDAO();
        idContaExterna = contaExternaDAO.buscarIdContaExterna(
                destinatario.getAgencia(),
                destinatario.getNumero(),
                destinatario.getCodigoBanco()
        );
        contaExternaDAO.fecharConexao();
        return idContaExterna;
    }

    public int buscarIdContaInterna(DestinatarioTransacao destinatarioTransacao) throws SQLException {
        ContaDAO contaDAO = new ContaDAO();
        return contaDAO.buscarIdContaPorAgenciaENumero(destinatarioTransacao.getAgencia(), destinatarioTransacao.getNumero());
    }

    public void cadastrarContaExterna(DestinatarioTransacao destinatario) throws SQLException {
        ContaExternaDAO contaExternaDAO = new ContaExternaDAO();
        contaExternaDAO.cadastrarContaExterna(
                destinatario.getAgencia(),
                destinatario.getNumero(),
                destinatario.getCodigoBanco()
        );
        contaExternaDAO.fecharConexao();
    }
}
