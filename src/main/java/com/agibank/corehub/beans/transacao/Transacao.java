package com.agibank.corehub.beans.transacao;

public class Transacao {
    private int id;
    private int idContaOrigem;
    private int idContaDestino;
    private double valor;
    private String descricao;
    private int idTipoTransacao;
    private boolean transferenciaExterna;

    public Transacao() {
    }

    public Transacao(int idContaOrigem, int idContaDestino, double valor, int idTipoTransacao, boolean transferenciaExterna) {
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.idTipoTransacao = idTipoTransacao;
        this.transferenciaExterna = transferenciaExterna;
    }

    public Transacao(int idContaOrigem, int idContaDestino, double valor, String descricao, int idTipoTransacao, boolean transferenciaExterna) {
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.descricao = descricao;
        this.idTipoTransacao = idTipoTransacao;
        this.transferenciaExterna = transferenciaExterna;
    }

    public Transacao(int id, int idContaOrigem, int idContaDestino, double valor, int idTipoTransacao, boolean transferenciaExterna) {
        this.id = id;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.idTipoTransacao = idTipoTransacao;
        this.transferenciaExterna = transferenciaExterna;
    }

    public Transacao(int id, int idContaOrigem, int idContaDestino, double valor, String descricao, int idTipoTransacao, boolean transferenciaExterna) {
        this.id = id;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.descricao = descricao;
        this.idTipoTransacao = idTipoTransacao;
        this.transferenciaExterna = transferenciaExterna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(int idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public int getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(int idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTipoTransacao() {
        return idTipoTransacao;
    }

    public void setIdTipoTransacao(int idTipoTransacao) {
        this.idTipoTransacao = idTipoTransacao;
    }

    public boolean getTransacaoExterna() {
        return transferenciaExterna;
    }

    public void setTransferenciaExterna(boolean transferenciaExterna) {
        this.transferenciaExterna = transferenciaExterna;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", idContaOrigem=" + idContaOrigem +
                ", idContaDestino=" + idContaDestino +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", idTipoTransacao=" + idTipoTransacao +
                ", transferenciaExterna=" + transferenciaExterna +
                '}';
    }
}

