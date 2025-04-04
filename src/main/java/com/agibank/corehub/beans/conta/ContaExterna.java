package com.agibank.corehub.beans.conta;

public class ContaExterna {
    private int idContaExterna;
    private int agencia;
    private int numeroContaExterna;
    private int codigoBanco;

    public ContaExterna() {
    }

    public ContaExterna(int agencia, int numeroContaExterna, int codigoBanco) {
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBanco = codigoBanco;
    }

    public ContaExterna(int idContaExterna, int agencia, int numeroContaExterna, int codigoBanco) {
        this.idContaExterna = idContaExterna;
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBanco = codigoBanco;
    }

    public int getIdContaExterna() {
        return idContaExterna;
    }

    public void setIdContaExterna(int idContaExterna) {
        this.idContaExterna = idContaExterna;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroContaExterna() {
        return numeroContaExterna;
    }

    public void setNumeroContaExterna(int numeroContaExterna) {
        this.numeroContaExterna = numeroContaExterna;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
}