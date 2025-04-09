package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.StatusTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.conta.ContaController;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import java.sql.SQLException;

public class VerificacaoTransacaoController {
    private static final IntegridadeTransacaoController integridadeTransacaoController = new IntegridadeTransacaoController();
    private static final SegurancaTransacaoController segurancaTransacaoController = new SegurancaTransacaoController();

    public void verificarTransacao(Transacao transacao) throws SQLException {
        if (integridadeTransacaoController.verificarIntegridadeTransacaoParaCadastrarEmStatus(transacao)){
            if(segurancaTransacaoController.verificarSegurancaTransacao(transacao)){

            }
        }
    }

    private int atualizarSaldo(StatusTransacao statusTransacao, Transacao transacao){
        try{
            StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
            ContaController controller = new ContaController();
            TransacaoDAO transacaoDAO = new TransacaoDAO();

            if(statusTransacao.getStatus().equals("APROVADO")){
                controller.atualizarSaldo(transacao.getIdContaOrigem(), -transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                if(transacao.getTransacaoExterna()){
                    controller.atualizarSaldo(transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getIdContaDestino(), transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                }
            }
            return 1;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {

        }
        return -1;
    }
}