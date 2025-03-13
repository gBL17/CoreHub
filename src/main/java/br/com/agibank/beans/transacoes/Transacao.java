package br.com.agibank.beans.transacoes;

public class Transacao {
    private int id;
    private int tipo;
    private int idConta;
    private int contaOrigem;
    private int contaDestino;
    private double valor;
    private String descricao;

    public Transacao() {
    }

    public Transacao(int tipo, int idConta,int contaOrigem, double valor) {
        this.tipo = tipo;
        this.idConta = idConta;
        this.contaOrigem = contaOrigem;
        this.valor = valor;
    }

    public Transacao(int tipo, int idConta, int contaOrigem, int contaDestino, double valor) {
        this.tipo = tipo;
        this.idConta = idConta;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Transacao(int tipo, int idConta, int contaOrigem, int contaDestino, double valor, String descricao) {
        this.tipo = tipo;
        this.idConta = idConta;
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


    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
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

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", idConta=" + idConta +
                ", contaOrigem=" + contaOrigem +
                ", contaDestino=" + contaDestino +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
