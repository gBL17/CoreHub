package com.agibank.corehub.beans;

public class Documento {
    private int id_documento;
    private int id_usuario;
    private String tipo;
    private String numero;
    private String arquivo;

    public Documento(int id_documento, int id_usuario, String tipo, String numero, String arquivo) {
        this.id_documento = id_documento;
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.numero = numero;
        this.arquivo = arquivo;
    }

    public Documento(int idUsuario, String tipo, String numero, String arquivo) {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
