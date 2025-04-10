package com.agibank.corehub.controller.utils;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;

public class DestinatarioTransacaoController {
    private static DestinatarioTransacaoController instancia;
    private DestinatarioTransacao destinatarioTransacao;

    private DestinatarioTransacaoController() {}

    public static DestinatarioTransacaoController getInstance(){
        if (instancia == null){
            instancia = new DestinatarioTransacaoController();
        }
        return instancia;
    }

    public void setDestinatarioTransacao(DestinatarioTransacao destinatarioTransacao){
        this.destinatarioTransacao = destinatarioTransacao;
    }

    public DestinatarioTransacao getDestinatarioTransacao(){
        return destinatarioTransacao;
    }
}
