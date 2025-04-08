package com.agibank.corehub.controller;

import java.time.LocalDate;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class ScoreController{
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();
    private final ContaDAO contaDAO = new ContaDAO();
    public ScoreController() throws SQLException {
    }

    public double retornarSomaValorMes(int id_conta){
        try {
            return transacaoDAO.SomarMovimentacaoPorMes(id_conta);
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        return 0;
    }

    public double calcularScorePorValor(int id_conta){
        try{
            return transacaoDAO.SomarMovimentacaoPorMes(id_conta)*0.1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean verificarTempoCadastro(LocalDate dataCadastro){
        LocalDate dataAtual = LocalDate.now();
        long mesesDesdeCadastro = ChronoUnit.MONTHS.between(dataCadastro, dataAtual);

        if(mesesDesdeCadastro % 6 == 0 && dataAtual.getDayOfMonth() == dataCadastro.getDayOfMonth()) return true;
        else return false;
    }

    public double atualizarScore(int id_conta, LocalDate UltimaDataAcesso, LocalDate dataCadastro){
        LocalDate dataAtual = LocalDate.now();
        long mesesDesdeCadastro = ChronoUnit.MONTHS.between(dataCadastro, dataAtual);
        double pontuacaoValor = 0;

        try{
            if(UltimaDataAcesso.getMonthValue() < dataAtual.getMonthValue()) pontuacaoValor = contaDAO.atualizarScoreConta(calcularScorePorValor(id_conta), id_conta);
            if(verificarTempoCadastro(dataCadastro)) return pontuacaoValor + 10;
            return pontuacaoValor;
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }

        return 0;
    }

}


