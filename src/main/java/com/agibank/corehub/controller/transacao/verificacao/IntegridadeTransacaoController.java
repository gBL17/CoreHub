package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;

import java.sql.SQLException;

public class IntegridadeTransacaoController {
    public void verificarIntegridadeTransacaoParaCadastrarEmStatus(Transacao transacao) throws SQLException {
        //todo verificações de integridade de transação
        cadastrarTransacao(transacao);
        int idTransacao = retornaIdTransacao(transacao);
        cadastrarTransacaoEmAnalise(idTransacao);
        buscarIdStatusTransacao(idTransacao);
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

    public int atualizarStatusTransacao(int idTransacao, String status) throws SQLException{
        int statusTransacao;
        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacao = statusTransacaoDAO.atualizarStatusTransacao(idTransacao, status);
        statusTransacaoDAO.fecharConexao();
        return statusTransacao;
    }

    public int buscarIdStatusTransacao(int idTransacao) throws SQLException{
        int idStatusTransacao;
        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        idStatusTransacao = statusTransacaoDAO.buscarIdStatusTransacao(idTransacao);
        statusTransacaoDAO.fecharConexao();
        return idStatusTransacao;
    }
}
