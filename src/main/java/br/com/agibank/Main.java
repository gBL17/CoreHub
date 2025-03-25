package br.com.agibank;

import br.com.agibank.view.transacoes.MenuTransacao;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException{
        Scanner sc = new Scanner(System.in);
//        MenuInicial.exibirMenuInicial(sc);
        try{
            MenuTransacao.exibirMenuTransacao(1, 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}