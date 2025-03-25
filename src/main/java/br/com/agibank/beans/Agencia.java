package br.com.agibank.beans;

public class Agencia {
    private String rua;
    private int numero;
    private String complemento;
    private String cidade;
    private int numeroAgencia;

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Agencia(String rua, int numero, String complemento,String cidade, int numeroAgencia) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.numeroAgencia = numeroAgencia;
    }

    public Agencia(){
    }
}
