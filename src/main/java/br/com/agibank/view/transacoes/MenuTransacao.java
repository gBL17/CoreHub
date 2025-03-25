package br.com.agibank.view.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.controller.transacoes.TransacoesInternasController;
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
        System.out.println("QUAL E A OPERACAO DESEJADA?: ");
        System.out.println("1. PIX");
        System.out.println("2. TED");
        System.out.println("2. DOC");
        System.out.println("3. SAIR");
        System.out.print("-> ");
        tipoTransacao = sc.nextInt();
        System.out.println("CERTO! AGORA DIGITE O CODIGO DO BANCO DESEJADO");
        System.out.print("-> ");
        codigoBancoContaDestino  = sc.nextInt();
        System.out.println("EXATAMENTE! AGORA INFORME A CONTA DESEJADA: ");
        System.out.println("AGÊNCIA: ");
        System.out.print("-> ");
        agenciaContaDestino  = sc.nextInt();
        System.out.println("AGORA O NÚMERO DA CONTA: ");
        System.out.print("-> ");
        numeroContaDestino = sc.nextInt();
        System.out.println("ESTAMOS QUASE LÁ! QUAL O VALOR DA TRANSAÇÃO?");
        System.out.print("-> ");
        valor  = sc.nextInt();
        sc.nextLine();
        System.out.println("QUAL O MOTIVO DA TRANSAÇÃO?");
        System.out.print("-> ");
        descricao  = sc.nextLine();
        sc.close();
        TransacoesInternasController.cadastraTransacao(idContaOrigem, idContaDestino, tipoTransacao, codigoBancoContaDestino, agenciaContaDestino, numeroContaDestino, valor, descricao);
    }
}
