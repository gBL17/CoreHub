package com.agibank.corehub.beans.transacao;

public class DestinatarioTransacao {
    private int codigoBanco;
    private int agencia;
    private int numero;

    public DestinatarioTransacao() {
    }

    public DestinatarioTransacao(int codigoBanco, int agencia, int numero) {
        this.codigoBanco = codigoBanco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DestinatarioTransacao{" +
                "codigoBanco=" + codigoBanco +
                ", agencia=" + agencia +
                ", numero=" + numero +
                '}';
    }
}
