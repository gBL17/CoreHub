package br.com.agibank.beans.conta;

import java.time.LocalDate;
import java.util.Date;

public class ContaSalario {

    private int idContaSalario;
    private int idConta;
    private int cnpj;
    private Date dataPagamento;
    private double valorPagamento;

    public ContaSalario(int idContaSalario, int idConta, int cnpj, Date dataPagamento, double valorPagamento) {
        this.idContaSalario = idContaSalario;
        this.idConta = idConta;
        this.cnpj = cnpj;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
    }

    public ContaSalario(){}

    public int getIdContaSalario() {
        return idContaSalario;
    }

    public void setIdContaSalario(int idContaSalario) {
        this.idContaSalario = idContaSalario;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
}