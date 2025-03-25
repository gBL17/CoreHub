package br.com.agibank.view;

import br.com.agibank.beans.Usuario;
import br.com.agibank.controller.LoginController;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuLogin {
    public static void login() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println(CoresTerminal.getBLUE() + "Login: " + CoresTerminal.getRESET() );
        System.out.print(CoresTerminal.getYELLOW() + "Usuario: " + CoresTerminal.getRESET());
        String usuario = sc.nextLine();
        System.out.print(CoresTerminal.getYELLOW() + "Senha: " + CoresTerminal.getRESET());
        String senha = sc.nextLine();

        Usuario usuarioVerificado = LoginController.processarLogin(usuario,senha);
        if( usuarioVerificado!= null){

            System.out.println(CoresTerminal.getBLUE() + "SEJA BEM VINDO(A) " + usuarioVerificado.getApelido() + CoresTerminal.getRESET());
            if(usuarioVerificado.getFuncionario())  MenuFuncionario.exibirMenuFuncionario(usuarioVerificado.getId_Usuario());
            else  MenuCliente.exibirMenuCliente(usuarioVerificado.getId_Usuario());

        } else {
            System.out.println(CoresTerminal.getRED() +"Usuario ou senha incorretos!" + CoresTerminal.getRESET());
            login();
        }

    }
}
