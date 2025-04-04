package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.dao.ContaExternaDAO;
import com.agibank.corehub.dao.conta.ContaDAO;

import java.sql.SQLException;

public class IdContaDestinoController {
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
