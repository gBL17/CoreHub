package br.com.agibank.view;

import br.com.agibank.view.transacoes.MenuTransacao;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuCliente {
    public static void exibirMenuCliente(int id_usuario) throws SQLException {
        //ClearTerminal.clearTerminal();
        Scanner sc = new Scanner(System.in);
        int interacao;
        do{
            System.out.println(CoresTerminal.getYELLOW() + "MENU CLIENTE: " + CoresTerminal.getRESET());
            System.out.println(CoresTerminal.getBLUE() + "1. " + CoresTerminal.getRESET() + "VERIFICAR SALDO");
            System.out.println(CoresTerminal.getBLUE() + "2. " + CoresTerminal.getRESET() + "REALIZAR TRANSACAO");
            System.out.println(CoresTerminal.getBLUE() + "3. " + CoresTerminal.getRESET() + "SOLICITAR SUPORTE");
            System.out.println(CoresTerminal.getBLUE() + "4. " + CoresTerminal.getRESET() + "VERIFICAR BENEFICIOS");
            System.out.println(CoresTerminal.getBLUE() + "5. " + CoresTerminal.getRESET() + "MEUS DADOS");
            System.out.println(CoresTerminal.getBLUE() + "6. " + CoresTerminal.getRESET() + "VER EXTRATO");
            System.out.println(CoresTerminal.getBLUE() + "7. " + CoresTerminal.getRESET() + "DADOS DA CONTA");
            System.out.println(CoresTerminal.getBLUE() + "8. " + CoresTerminal.getRESET() + "SAIR");
            System.out.print("-> ");
            interacao = sc.nextInt();

            switch (interacao){
                case 1:
                    System.out.println("SALDO NA CONTA:");
                    break;
                case 2:
//                    MenuTransacao.exibirMenuTransacao(id_usuario);
                    break;
                case 3:
//                    MenuSuporte.exibirMenuSuporte(id_usuario);
                    break;
                case 4:
                    System.out.println("BENEFICIOS DA SUA CLASSE:");
                    break;
                case 5:
//                    MenuDadosUsuario.exibirMenuDadosUsuario(id_usuario);
                    break;
                case 6:
                    System.out.println("EXTRATO:");
                    break;
                case 7:
                    System.out.println("DADOS DA CONTA:");
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Insira uma opcao valida!");
            }

        } while (interacao != 8);

    }
}
