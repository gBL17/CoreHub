package com.agibank.corehub.beans.conta;

public class ContaExterna {

    private int idContaExterna;
    private String dadosContaExterna;

    public ContaExterna(int idContaExterna, String dadosContaExterna) {
        this.idContaExterna = idContaExterna;
        this.dadosContaExterna = dadosContaExterna;
    }

    public ContaExterna(){}

    public int getIdContaExterna() {
        return idContaExterna;
    }

    public void setIdContaExterna(int idContaExterna) {
        this.idContaExterna = idContaExterna;
    }

    public String getDadosContaExterna() {
        return dadosContaExterna;
    }

    public void setDadosContaExterna(String dadosContaExterna) {
        this.dadosContaExterna = dadosContaExterna;
    }
}
