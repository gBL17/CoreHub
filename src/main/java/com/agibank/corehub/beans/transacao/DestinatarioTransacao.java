package com.agibank.corehub.beans.transacao;

public class DestinatarioTransacao {
    private String codigoBanco;
    private String agencia;
    private String numero;

    public DestinatarioTransacao() {
    }

    public DestinatarioTransacao(String codigoBanco, String agencia, String numero) {
        this.codigoBanco = codigoBanco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DestinatarioTransacao{" +
                "codigoBanco='" + codigoBanco + '\'' +
                ", agencia='" + agencia + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
