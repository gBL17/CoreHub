package com.agibank.corehub.beans;

public class Suporte {
    private int idCliente;
    private int idFuncionario;
    private String descricao;
    private String resolucao;

    public Suporte(int idCliente,String descricao){
        this.descricao = descricao;
        this.idCliente = idCliente;
    }

    public Suporte(){
    }

    public int getidCliente() {
        return idCliente;
    }

    public void setidCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getidFuncionario() {
        return idFuncionario;
    }

    public void setidFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
}


