package com.agibank.corehub.beans.conta;

public class ContaPoupanca {

    private int idPoupanca;
    private double limiteTrasacao;
    private double rendimento;
    private int idConta;

    public ContaPoupanca(int idPoupanca, double limiteTrasacao, double rendimento, int idConta) {
        this.idPoupanca = idPoupanca;
        this.limiteTrasacao = limiteTrasacao;
        this.rendimento = rendimento;
        this.idConta = idConta;
    }

    public ContaPoupanca(){}

    public int getIdPoupanca() {
        return idPoupanca;
    }

    public void setIdPoupanca(int idPoupanca) {
        this.idPoupanca = idPoupanca;
    }

    public double getLimiteTrasacao() {
        return limiteTrasacao;
    }

    public void setLimiteTrasacao(double limiteTrasacao) {
        this.limiteTrasacao = limiteTrasacao;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
