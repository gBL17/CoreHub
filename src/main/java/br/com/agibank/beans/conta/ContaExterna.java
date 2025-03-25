package br.com.agibank.beans.conta;

public class ContaExterna {

    private int idContaExterna;
    private int agencia;
    private int numeroContaExterna;
    private int codigoBancoExterno;

    public ContaExterna() {
    }

    public ContaExterna(int agencia, int numeroContaExterna, int codigoExterno) {
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBancoExterno = codigoExterno;
    }

    public ContaExterna(int idContaExterna, int agencia, int numeroContaExterna, int codigoExterno) {
        this.idContaExterna = idContaExterna;
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBancoExterno = codigoExterno;
    }

    public int getIdContaExterna() {
        return idContaExterna;
    }

    public void setIdContaExterna(int idContaExterna) {
        this.idContaExterna = idContaExterna;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroContaExterna() {
        return numeroContaExterna;
    }

    public void setNumeroContaExterna(int numeroContaExterna) {
        this.numeroContaExterna = numeroContaExterna;
    }

    public int getCodigoBancoExterno() {
        return codigoBancoExterno;
    }

    public void setCodigoBancoExterno(int codigoBancoExterno) {
        this.codigoBancoExterno = codigoBancoExterno;
    }
}
