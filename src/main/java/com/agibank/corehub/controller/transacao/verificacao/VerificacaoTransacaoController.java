package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;

import java.sql.SQLException;

public class VerificacaoTransacaoController {
    public void verificarTransacao(Transacao transacao) throws SQLException {
        IntegridadeTransacaoController integridadeTransacaoController = new IntegridadeTransacaoController();
        SegurancaTransacaoController segurancaTransacaoController = new SegurancaTransacaoController();
        integridadeTransacaoController.verificarIntegridadeTransacao(transacao);
        segurancaTransacaoController.verificarSegurançaTransacao(transacao);
    }
}
