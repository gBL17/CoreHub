package br.com.agibank.view;

import br.com.agibank.beans.Usuario;
import br.com.agibank.controller.UsuarioController;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuDadosUsuario {
    static UsuarioController usuarioController;
    static Usuario su = new Usuario();

    static {
        try {
            usuarioController = new UsuarioController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Scanner sc = new Scanner(System.in);

    public MenuDadosUsuario() throws SQLException {
    }

    public static void exibirMenuDadosUsuario(int id_usuario) throws SQLException {
        int interacao;
        do{
            System.out.println(CoresTerminal.getYELLOW() + "QUAL E A OPERACAO DESEJADA?: " + CoresTerminal.getRESET());
            System.out.println("1. VER DADOS");
            System.out.println("2. ATUALIZAR DADOS");
            System.out.println("3. SAIR");
            System.out.print("-> ");
            interacao  = sc.nextInt();
            switch (interacao){
                case 1:
                    //TODO adicionar mais dados
                    System.out.println("MEUS DADOS");
                    Usuario usuario = usuarioController.buscarDadosUsuario(id_usuario);
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.println("Apelido: " + usuario.getApelido());
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Insira uma opcao valida");
            }
        }while(interacao!=3);
    }
}
