package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.VerificacaoSegurancaDAO;

import java.sql.SQLException;
import java.time.LocalTime;

public class SegurancaTransacaoController {
    public void verificarSegurancaTransacao(Transacao transacao) throws SQLException {
        //todo Adicionar metodos de segurança
        VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
        double media = (int) verificacaoSegurancaDAO.mediaValores(transacao.getIdContaOrigem());
        double maior = (int) verificacaoSegurancaDAO.maiorValor(transacao.getIdContaOrigem());
        double valor = transacao.getValor();

        verificarHorario();
        verificarMediaValor(valor, media);
        verificarMaiorValor(valor, maior);

        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacaoDAO.atualizarStatusTransacao(retornaIdTransacao(transacao), "APROVADA");
        statusTransacaoDAO.fecharConexao();


    }

    public boolean verificarHorario() throws  SQLException {
        LocalTime horarioAtual = LocalTime.now();
        LocalTime start = LocalTime.of(6, 0);  // 6:00 AM
        LocalTime end = LocalTime.of(22, 0);   // 10:00 PM

        if (!(horarioAtual.isAfter(start) && horarioAtual.isBefore(end))) {
            System.out.println("Transação fora do horário permitido.");
            return  false;
        }
        return true;
    }

    public boolean verificarMaiorValor(double valor ,double maiorValor) throws  SQLException {
        if(valor > maiorValor) {
            System.out.println("Transação excede o maior valor permitido.");
            return false;
        }
        return true;
    }

    public boolean verificarMediaValor(double valor, double mediaValores) throws  SQLException {
        if(valor > mediaValores * 1.5) {
            System.out.println("Transação acima do limite baseado na média.");
            return false;
        }
        return true;
    }

    public int retornaIdTransacao(Transacao transacao) throws SQLException{
        int idTransacao;
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        idTransacao = transacaoDAO.buscarIdTransacao(transacao);
        transacaoDAO.fecharConexao();
        return idTransacao;
    }
}
