package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.utils.Navegador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TipoTransacaoController implements Initializable {
    private Navegador navegador = new Navegador();
    private Transacao transacao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TransacaoController.getInstance().setTransacao(new Transacao());
        transacao = TransacaoController.getInstance().getTransacao();
    }

    @FXML
    private void navegarParaTransacaoPix(ActionEvent actionEvent) throws IOException {
        transacao.setIdTipoTransacao(1); // PIX
        navegarContaTransacao(actionEvent);
    }

    @FXML
    private void navegarParaTransacaoDoc(ActionEvent actionEvent) throws IOException {
        transacao.setIdTipoTransacao(2); // DOC
        navegarContaTransacao(actionEvent);
    }

    @FXML
    private void navegarParaTransacaoTed(ActionEvent actionEvent) throws IOException {
        transacao.setIdTipoTransacao(3); // TED
        navegarContaTransacao(actionEvent);
    }

    @FXML
    private void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "contaTransacao.fxml");
    }

    @FXML
    private void voltarConta(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent,"conta.fxml");
    }

}
