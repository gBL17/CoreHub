package com.agibank.corehub.controller;

import java.sql.SQLException;
import java.time.LocalTime;

public class VerificacaoSegurancaController {

    public VerificacaoSegurancaController() throws SQLException { }

    public boolean verificacaoSeguranca(double mediaValores, double valor, double maiorValor) throws SQLException {
        boolean resultado = Boolean.parseBoolean(null);
        LocalTime horarioAtual = LocalTime.now();
        LocalTime start = LocalTime.of(6, 0);  // 6:00 AM
        LocalTime end = LocalTime.of(22, 0);   // 10:00 PM
//        if ((valor >= mediaValores) && (valor <= maiorValor) && horarioAtual.isAfter(start) && horarioAtual.isBefore(end)) {
//            resultado = true;
//        }

        if (!(horarioAtual.isAfter(start) && horarioAtual.isBefore(end))) {
            System.out.println("Transação fora do horário permitido.");
            return  false;
        }

        if(valor > maiorValor) {
            System.out.println("Transação excede o maior valor permitido.");
            return false;
        }

        if(valor > mediaValores * 1.5) {
            System.out.println("Transação acima do limite baseado na média.");
            return false;
        }

        System.out.println("Transação permitida.");
        return true;
    }

}
