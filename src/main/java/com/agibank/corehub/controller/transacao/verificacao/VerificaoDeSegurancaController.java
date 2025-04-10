package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.utils.Alerta;
import com.agibank.corehub.controller.conta.ContaController;
import com.agibank.corehub.controller.transacao.TransacaoController;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class VerificaoDeSegurancaController {
    private Navegador navegador = new Navegador();
    private static final Transacao transacao = TransacaoController.getInstance().getTransacao();
    @FXML
    private CheckBox checkBox1, checkBox2, checkBox3;

    @FXML
    private Button confirmarButton;

    @FXML
    public void initialize() {
        confirmarButton.disableProperty().bind(
                checkBox1.selectedProperty().not()
                        .or(checkBox2.selectedProperty().not())
                        .or(checkBox3.selectedProperty().not())
        );
    }

    public void confirmarTransacao(ActionEvent actionEvent) {
        try{
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
            statusTransacaoDAO.atualizarStatusTransacao(transacaoDAO.buscarIdTransacao(transacao), "APROVADA");
            atualizarSaldo(actionEvent);
        } catch (SQLException e){
            Alerta.exibirAlertaErro("Erro ao Atualizar Saldo", e.getMessage());
        }
    }

    public void cancelarTransacao(ActionEvent actionEvent) {
        try{
            StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
            statusTransacaoDAO.atualizarStatusTransacao(transacao.getId(), "CANCELADA");
            navegador.navegarPara(actionEvent, "conta.fxml");
            Alerta.exibirAlertaErro("Transação Cancelada", "Para sua segurança cancelamos a transação");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void atualizarSaldo(ActionEvent actionEvent){
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
            navegador.navegarPara(actionEvent, "conta.fxml");
        }catch (SQLException | IOException e){
            Alerta.exibirAlertaErro("Erro ao Atualizar Saldo", e.getMessage());
        }
    }
}


