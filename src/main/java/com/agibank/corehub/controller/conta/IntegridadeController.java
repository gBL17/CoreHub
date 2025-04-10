package com.agibank.corehub.controller.conta;

import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.beans.conta.ContaCorrente;
import com.agibank.corehub.beans.conta.ContaPoupanca;
import com.agibank.corehub.beans.conta.ContaSalario;
import com.agibank.corehub.beans.transacao.Transacao;

public class IntegridadeController {
    public boolean verificarTransacaoContaOrigemDiferenteContaDestino(Transacao transacao){
        if (transacao.getIdContaOrigem() != transacao.getIdContaDestino()) return true;
        else return false;
    }

    public boolean VerificarSaldoDisponivel (Conta conta, Transacao transacao) {
        if (conta.getSaldo() < transacao.getValor()) return false;
        return true;
    }

    public boolean verificarSaldoSalario(ContaSalario contaSalario, Transacao transacao){
        if (contaSalario.getIdContaSalario() < transacao.getValor()) return false;
        return true;
    }


    public boolean verificarSaldoCorrente (ContaCorrente contaCorrente, Transacao transacao){
        if (contaCorrente.getIdContaCorrente() < transacao.getValor()) return false;
        return true;
    }

    public boolean VerificarPoupanca (ContaPoupanca contaPoupanca, Transacao transacao) {
        if (contaPoupanca.getIdPoupanca() < transacao.getValor()) return false;
        return true;
    }
}
