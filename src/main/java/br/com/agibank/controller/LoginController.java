package br.com.agibank.controller;

import br.com.agibank.beans.Usuario;
import br.com.agibank.dao.UsuarioDAO;

import java.sql.SQLException;

public class LoginController {

    public static Usuario processarLogin(String usuario, String senha) throws SQLException {
        CifradorSenha cifrador = new CifradorSenha();

        try{
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuarioVerificado = usuarioDao.buscarUsuarioApelido(usuario);
            if( usuarioVerificado != null && cifrador.validarSenhaCrifrada(usuarioVerificado.getSenha(),senha)) return usuarioVerificado;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
