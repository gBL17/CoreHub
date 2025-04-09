package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.beans.transacao.Transacao;

import java.sql.SQLException;

public class VerificacaoTransacaoController {
    public void verificarTransacao(Conta conta, Transacao transacao) throws SQLException {
        IntegridadeTransacaoController integridadeTransacaoController = new IntegridadeTransacaoController();
        SegurancaTransacaoController segurancaTransacaoController = new SegurancaTransacaoController();

        integridadeTransacaoController.verificarIntegridadeTransacaoParaCadastrarEmStatus(conta, transacao);
        segurancaTransacaoController.verificarSegurancaTransacao(transacao);
    }
}