package br.com.agibank.beans.conta;

import com.mysql.cj.xdevapi.JsonString;
import com.mysql.cj.xdevapi.JsonValue;

public class ContaExterna {

    private int idContaExterna;
    private int agencia;
    private int numeroContaExterna;

    public ContaExterna() {
    }

    public ContaExterna(int agencia, int numeroContaExterna) {
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
    }

    public ContaExterna(int idContaExterna, int agencia, int numeroContaExterna) {
        this.idContaExterna = idContaExterna;
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
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
}
