package com.agibank.corehub;

import com.agibank.corehub.controller.VerificacaoSegurancaController;
import com.agibank.corehub.dao.VerificacaoSegurancaDAO;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

//        VerificacaoSegurancaController verificacaoSegurancaController = new VerificacaoSegurancaController();
//        boolean resultado = verificacaoSegurancaController.verificacaoSeguranca(2000, 4000, 3000 );
//        System.out.println(resultado);
        try {
        VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
        Date resultado = (Date) verificacaoSegurancaDAO.horarioTransacao(3);
        System.out.println(resultado);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
