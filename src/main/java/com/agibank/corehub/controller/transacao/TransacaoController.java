package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.Transacao;

public class TransacaoController {
    private static TransacaoController instancia;
    private Transacao transacao;

    private TransacaoController(){}

    public static TransacaoController getInstance(){
        if (instancia == null){
            instancia = new TransacaoController();
        }
        return instancia;
    }

    public void setTransacao(Transacao transacao){
        this.transacao = transacao;
    }

    public Transacao getTransacao(){
        return transacao;
    }
}
