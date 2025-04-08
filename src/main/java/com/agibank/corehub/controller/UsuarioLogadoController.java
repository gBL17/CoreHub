package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Usuario;

public class UsuarioLogadoController {
    private static UsuarioLogadoController instancia;
    private Usuario usuario;

    private UsuarioLogadoController(){}

    public static UsuarioLogadoController getInstance(){
        if (instancia == null){
            instancia = new UsuarioLogadoController();
        }
        return instancia;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return usuario;
    }
}
