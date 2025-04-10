package com.agibank.corehub.controller.conta;

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
            return transacaoDAO.SomarMovimentacaoPorMes(1)*0.2;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean verificarTempoCadastro(LocalDate dataCadastro){
        LocalDate dataAtual = LocalDate.now();
        long mesesDesdeCadastro = ChronoUnit.MONTHS.between(dataCadastro, dataAtual);

        return mesesDesdeCadastro % 6 == 0 && dataAtual.getDayOfMonth() == dataCadastro.getDayOfMonth();
    }

    public double atualizarScore(int id_conta, LocalDate UltimaDataAcesso, LocalDate dataCadastro){
        LocalDate dataAtual = LocalDate.now();
        double pontuacaoValor = 0;

        if(UltimaDataAcesso.getMonthValue() < dataAtual.getMonthValue()) pontuacaoValor = calcularScorePorValor(id_conta);

        try{
            if(verificarTempoCadastro(dataCadastro)) return contaDAO.atualizarScoreConta(pontuacaoValor + 10,id_conta);
            return contaDAO.atualizarScoreConta(pontuacaoValor,id_conta);
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }

        return 0;
    }

}


