package com.agibank.corehub;

import com.agibank.corehub.controller.VerificacaoSegurancaController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        VerificacaoSegurancaController verificacaoSegurancaController = new VerificacaoSegurancaController();
        boolean resultado = verificacaoSegurancaController.verificacaoSeguranca(2000, 4000, 3000 );
        System.out.println(resultado);

    }
}
