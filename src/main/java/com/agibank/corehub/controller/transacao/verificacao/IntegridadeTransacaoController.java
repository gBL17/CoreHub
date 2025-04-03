package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.dao.TransacaoDAO;

import java.sql.SQLException;

public class IntegridadeTransacaoController {
    public void verificarIntegridadeTransacao(Transacao transacao) throws SQLException {
        //todo verificações de integridade de transação

        cadastrarTransacao(transacao);
    }

    public void cadastrarTransacao(Transacao transacao) throws SQLException {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.criarTransacao(transacao);
    }
}
