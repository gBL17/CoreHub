package com.agibank.corehub.controller.conta;

import com.agibank.corehub.beans.conta.ContaPoupanca;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.conta.ContaPoupancaDAO;

import java.sql.SQLException;

public class ContaPoupancaController {
    TransacaoDAO transacaoDAO = new TransacaoDAO();
    ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();

    public ContaPoupancaController() throws SQLException {
    }

    private double calcularRendimento(int id_conta){
        double valor;
        double rendimento;
        ContaPoupanca conta = new ContaPoupanca();
        try{
            valor = transacaoDAO.SomarDepositosPorMes(id_conta);
            rendimento = contaPoupancaDAO.buscarContaPoupanca(id_conta).getRendimento();
            return valor*(1+rendimento);
        }catch (SQLException e){
            e.getMessage();
        }

        return 0;
    }

}
