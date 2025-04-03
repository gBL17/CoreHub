package com.agibank.corehub.beans.conta;

public class TipoConta {

    private int idTipoConta;
    private String tipo;

    public TipoConta(int idTipoConta, String tipo) {
        this.idTipoConta = idTipoConta;
        this.tipo = tipo;
    }

    public TipoConta(){}

    public int getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(int idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
