package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.StatusTransacao;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.controller.conta.ContaController;

import java.sql.SQLException;

public class StatusTransacaoController {

    StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
    ContaController controller = new ContaController();
    TransacaoDAO transacaoDAO = new TransacaoDAO();

    public StatusTransacaoController() throws SQLException {

    }

    public int criarStatusTransacao(StatusTransacao statusTransacao,boolean transacaoExterna){
        try{
            statusTransacaoDAO.criarStatusTransacao(statusTransacao);
            if(atualizarSaldo(statusTransacao,transacaoExterna) == 1)  return 1;
            else return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int atualizarSaldo(StatusTransacao statusTransacao, boolean transacaoExterna){

        try{
            if(statusTransacao.getStatus().equals("APROVADO")){
                controller.atualizarSaldo(transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getIdContaOrigem(), -transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                if(!transacaoExterna){
                    controller.atualizarSaldo(transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getIdContaDestino(), transacaoDAO.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                }
            }
            return 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }


}
