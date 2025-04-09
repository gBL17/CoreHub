package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;

import com.agibank.corehub.dao.conta.ContaDAO;
import java.sql.SQLException;

public class IntegridadeTransacaoController {
    private static TransacaoDAO transacaoDAO;
    private static StatusTransacaoDAO statusTransacaoDAO;
    private static ContaDAO contaDAO;

    public boolean verificarIntegridadeTransacaoParaCadastrarEmStatus(Transacao transacao) throws SQLException {
        try{
            transacaoDAO = new TransacaoDAO();
            statusTransacaoDAO = new StatusTransacaoDAO();
            contaDAO = new ContaDAO();
            //Cadastra transação
            transacaoDAO.criarTransacao(transacao);
            if (!verificarTransacaoContaOrigemDiferenteContaDestino(transacao)){
                statusTransacaoDAO.criarStatusTransacao(transacaoDAO.buscarIdTransacao(transacao), "CANCELADA");
                return false;
            }
            if (!verificarSaldoDisponivel(transacao)){
                statusTransacaoDAO.criarStatusTransacao(transacaoDAO.buscarIdTransacao(transacao), "CANCELADA");
                return false;
            }
            //Adicionar Status 'EM ANALISE'
            statusTransacaoDAO.criarStatusTransacao(transacaoDAO.buscarIdTransacao(transacao), "EM ANALISE");
            return true;
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro No Banco de dados", e.getMessage());
        } finally {
            transacaoDAO.fecharConexao();
            statusTransacaoDAO.fecharConexao();
            contaDAO.fecharConexao();
        }
        return false;
    }

    public boolean verificarTransacaoContaOrigemDiferenteContaDestino(Transacao transacao){
        if (transacao.getIdContaOrigem() != transacao.getIdContaDestino() && !transacao.getTransacaoExterna()) {
            return true;
        } else return transacao.getTransacaoExterna();
    }

    public boolean verificarSaldoDisponivel (Transacao transacao) throws SQLException {
        return (contaDAO.buscarSaldoConta(transacao.getIdContaOrigem()) > transacao.getValor());
    }
}
