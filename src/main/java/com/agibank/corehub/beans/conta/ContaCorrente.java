package com.agibank.corehub.beans.conta;

public class ContaCorrente {

    private int idContaCorrente;
    private int idConta;
    private int limiteCredito;

    public ContaCorrente(int idContaCorrente, int idConta, int limiteCredito) {
        this.idContaCorrente = idContaCorrente;
        this.idConta = idConta;
        this.limiteCredito = limiteCredito;
    }

    public ContaCorrente(){}

    public int getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(int idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}