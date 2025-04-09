package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.beans.conta.ContaCorrente;
import com.agibank.corehub.beans.conta.ContaPoupanca;
import com.agibank.corehub.beans.conta.ContaSalario;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;

import java.sql.SQLException;

public class IntegridadeTransacaoController {
    public void verificarIntegridadeTransacaoParaCadastrarEmStatus(Conta conta, Transacao transacao) throws SQLException {
        //todo verificações de integridade de transação
        verificarTransacaoContaOrigemDiferenteContaDestino(transacao);
        verificarSaldoDisponivel(conta, transacao);
        cadastrarTransacao(transacao);
        int idTransacao = retornaIdTransacao(transacao);
        cadastrarTransacaoEmAnalise(idTransacao);
    }

    public void cadastrarTransacao(Transacao transacao) throws SQLException {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.criarTransacao(transacao);
        transacaoDAO.fecharConexao();
    }

    public void cadastrarTransacaoEmAnalise(int idTransacao) throws SQLException {
        String status = "EM ANALISE";
        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacaoDAO.criarStatusTransacao(idTransacao, status);
        statusTransacaoDAO.fecharConexao();
    }

    public int retornaIdTransacao(Transacao transacao) throws SQLException{
        int idTransacao;
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        idTransacao = transacaoDAO.buscarIdTransacao(transacao);
        transacaoDAO.fecharConexao();
        return idTransacao;
    }

    public boolean verificarTransacaoContaOrigemDiferenteContaDestino(Transacao transacao){
        if ((transacao.getIdContaOrigem() != transacao.getIdContaDestino()) || ((transacao.getIdContaOrigem() == transacao.getIdContaDestino()) && (transacao.isTransferenciaExterna() != false))) return true;
        else return false;
    }

    public boolean verificarSaldoDisponivel (Conta conta, Transacao transacao) {
        if (conta.getSaldo() < transacao.getValor()) return false;
        return true;
    }



    public int atualizarStatusTransacao(int idTransacao, String status) throws SQLException{
        int statusTransacao;
        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacao = statusTransacaoDAO.atualizarStatusTransacao(idTransacao, status);
        statusTransacaoDAO.fecharConexao();
        return statusTransacao;
    }
}
