package com.agibank.corehub.beans.transacao;

public class TipoTransacao {
    private int id;
    private String tipoTransacao;

    public TipoTransacao() {
    }

    public TipoTransacao(int id, String tipoTransacao) {
        this.id = id;
        this.tipoTransacao = tipoTransacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
