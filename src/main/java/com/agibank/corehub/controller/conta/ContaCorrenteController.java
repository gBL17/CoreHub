package com.agibank.corehub.controller.conta;

import com.agibank.corehub.dao.conta.ContaCorrenteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;

public class ContaCorrenteController {

    private final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();

    public ContaCorrenteController() throws SQLException {
    }

    public int descontarSaldoContaCorrente(int idConta, LocalDate UltimaDataAcesso) throws SQLException{

        LocalDate dataAtual = LocalDate.now();

        if (UltimaDataAcesso.getMonthValue() < dataAtual.getMonthValue()) {
            return contaCorrenteDAO.descontarSaldoContaCorrente(idConta);
        }else{
            int diaAtual = LocalDate.now().getDayOfMonth();
            int anoMes = YearMonth.now().lengthOfMonth();

            return anoMes - diaAtual;
        }

    }
}
