package br.com.agibank.model.transacoes;

public class Transacao {
    private int id;
    private int tipo;
    private int contaOrigem;
    private int contaDestino;
    private double valor;
    private String descricao;

    public Transacao() {
    }

    public Transacao(int tipo, int contaOrigem, double valor) {
        this.tipo = tipo;
        this.contaOrigem = contaOrigem;
        this.valor = valor;
    }

    public Transacao(int tipo, int contaOrigem, int contaDestino, double valor) {
        this.tipo = tipo;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Transacao(int tipo, int contaOrigem, int contaDestino, double valor, String descricao) {
        this.tipo = tipo;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(int contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public int getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(int contaDestino) {
        this.contaDestino = contaDestino;
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
}
