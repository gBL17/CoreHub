package br.com.agibank.view.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.controller.transacoes.TransacoesController;
import br.com.agibank.view.CoresTerminal;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuTransacao {
    static Scanner sc = new Scanner(System.in);
    static Transacao transacao = new Transacao();
    static int interacao;
    static int idContaOrigem;
    static int idContaDestino;
    static int tipoTransacao;
    static int codigoBancoContaDestino;
    static int agenciaContaDestino;
    static int numeroContaDestino;
    static double valor;
    static String descricao;

    public static void exibirMenuTransacao(int idUsuario, int idConta) throws SQLException {
        idContaOrigem = idConta;
        System.out.println(CoresTerminal.getYELLOW() + "QUAL E A OPERACAO DESEJADA?: " + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "1. PIX" + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "2. TED" + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "3. DOC" + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "4. SAIR" + CoresTerminal.getRESET());
        System.out.print("-> ");
        tipoTransacao = sc.nextInt();

        System.out.println(CoresTerminal.getYELLOW() + "CERTO! AGORA DIGITE O CODIGO DO BANCO DESEJADO" + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "Codigo do Banco:" + CoresTerminal.getRESET());
        System.out.print("-> ");
        codigoBancoContaDestino  = sc.nextInt();

        System.out.println(CoresTerminal.getYELLOW() + "EXATAMENTE! AGORA INFORME A CONTA DESEJADA: " + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "AGÊNCIA: " + CoresTerminal.getRESET());
        System.out.print("-> ");
        agenciaContaDestino  = sc.nextInt();

        System.out.println(CoresTerminal.getYELLOW() + "AGORA O NÚMERO DA CONTA: " + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "NÚMERO DA CONTA: " + CoresTerminal.getRESET());
        System.out.print("-> ");
        numeroContaDestino = sc.nextInt();

        System.out.println(CoresTerminal.getYELLOW() + "ESTAMOS QUASE LÁ!" + CoresTerminal.getRESET());
        System.out.println(CoresTerminal.getBLUE() + "VALOR DA TRANSAÇÃO: " + CoresTerminal.getRESET());
        System.out.print("-> ");
        valor  = sc.nextInt();

        sc.nextLine();

        System.out.println(CoresTerminal.getYELLOW() + "QUAL O MOTIVO DA TRANSAÇÃO?" + CoresTerminal.getRESET());
        System.out.print("-> ");
        descricao  = sc.nextLine();
        sc.close();

        TransacoesController.cadastraTransacao(idContaOrigem, idContaDestino, tipoTransacao, codigoBancoContaDestino, agenciaContaDestino, numeroContaDestino, valor, descricao);
    }
}
