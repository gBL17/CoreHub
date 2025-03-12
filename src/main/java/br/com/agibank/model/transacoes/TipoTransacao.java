package br.com.agibank.model.transacoes;

public class TipoTransacao {
    private int id;
    private String tipo;

    public TipoTransacao() {
    }

    public TipoTransacao(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
