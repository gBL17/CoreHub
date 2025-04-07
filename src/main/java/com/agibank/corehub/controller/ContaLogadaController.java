package com.agibank.corehub.controller;

import com.agibank.corehub.beans.conta.Conta;

public class ContaLogadaController {
    private static ContaLogadaController instancia;
    private Conta conta;

    private ContaLogadaController(){}

    public static ContaLogadaController getInstance(){
        if (instancia == null){
            instancia = new ContaLogadaController();
        }
        return instancia;
    }

    public void setConta(Conta conta){
        this.conta = conta;
    }

    public Conta getConta(){
        return conta;
    }
}
