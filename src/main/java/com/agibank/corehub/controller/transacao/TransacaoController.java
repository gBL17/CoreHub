package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.dao.ContaExternaDAO;
import java.sql.SQLException;

public class TransacaoController {
    public int buscarIdContaDestino(DestinatarioTransacao destinatario) throws SQLException {
        int idContaDestino = 0;
        if (destinatario.getCodigoBanco() != 121){
            idContaDestino = buscarIdContaExterna(destinatario);
            if (idContaDestino == 0){
                cadastrarContaExterna(destinatario);
                idContaDestino = buscarIdContaDestino(destinatario);
            }
        } else {
            idContaDestino = buscarIdContaDestino(destinatario);
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

    public int buscarIdContaInterna(DestinatarioTransacao destinatario){
        //todo buscar id em caso de conta interna;
        return 0;
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
