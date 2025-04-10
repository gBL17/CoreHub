package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.utils.Alerta;
import com.agibank.corehub.controller.conta.ContaController;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import javafx.event.ActionEvent;

public class ConfirmarTransacao {
    private static final IntegridadeTransacaoController integridadeTransacaoController = new IntegridadeTransacaoController();
    private static final SegurancaTransacaoController segurancaTransacaoController = new SegurancaTransacaoController();
    private static final Navegador navegador = new Navegador();

    public void confirmarTransacao(ActionEvent actionEvent, Transacao transacao) throws SQLException, IOException {
        if (integridadeTransacaoController.verificarIntegridadeTransacaoParaCadastrarEmStatus(transacao)){
            if(segurancaTransacaoController.verificarSegurancaTransacao(actionEvent, transacao)){
                atualizarSaldo(transacao);
                Alerta.exibirAlertaSucesso("Transacao Cadastrada com sucesso!", "Aguardando verificação");
                navegador.navegarPara(actionEvent, "conta.fxml");
            }
        }
    }

    private void atualizarSaldo(Transacao transacao){
        try{
            StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
            ContaController controller = new ContaController();
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            transacao.setId(transacaoDAO.buscarIdTransacao(transacao));

            if(Objects.equals(statusTransacaoDAO.buscarUltimoStatusTransacao(transacao.getId()), "APROVADA")){
                controller.atualizarSaldoInterno(transacao.getIdContaOrigem(), -transacaoDAO.buscarTransacaoPorId(transacao.getId()).getValor());
                if(!transacao.getTransacaoExterna()){
                    controller.atualizarSaldoInterno(transacaoDAO.buscarTransacaoPorId(transacao.getId()).getIdContaDestino(), transacaoDAO.buscarTransacaoPorId(transacao.getId()).getValor());
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}