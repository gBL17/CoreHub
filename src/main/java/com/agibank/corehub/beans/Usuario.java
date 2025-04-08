package com.agibank.corehub.beans;

import java.time.LocalDate;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String apelido;
    private String senha;
    private String email;
    private int telefone;
    private LocalDate data_nascimento;
    private String rua;
    private int numero;
    private String complemento;
    private boolean funcionario;
    private LocalDate data_ultimo_acesso;

    public Usuario(String nome, String apelido, String senha, String email, int telefone, LocalDate data_nascimento, String rua, int numero, String complemento, boolean funcionario) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.funcionario = funcionario;
    }

    public Usuario(int id_usuario, String nome, String apelido, String senha, String email, int telefone, LocalDate data_nascimento, String rua, int numero, String complemento) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Usuario(int id_usuario, String nome, String apelido, String senha, String email, int telefone, LocalDate data_nascimento, String rua, int numero) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.rua = rua;
        this.numero = numero;
    }

    public Usuario() {

    }

    public int getId_Usuario() {
        return id_usuario;
    }

    public void setId(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean getFuncionario(){
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate geUltimoAcesso(){
        return data_ultimo_acesso;
    }

    public void setUltimoAcesso (LocalDate data) {
        this.data_ultimo_acesso = data;
    }
}