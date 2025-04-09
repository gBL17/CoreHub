package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import java.io.IOException;

import com.agibank.corehub.controller.utils.DestinatarioTransacaoController;
import com.agibank.corehub.controller.utils.Navegador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ContaTransacaoController implements Initializable {
    private Navegador navegador = new Navegador();
    private DestinatarioTransacao destinatarioTransacao;
    private Transacao transacao = TransacaoController.getInstance().getTransacao();

    @FXML
    private Label tipoTransacaoLabel;

    @FXML
    private TextField codigoBancoTextField;

    @FXML
    private TextField agenciaTextField;

    @FXML
    private TextField numeroContaTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(transacao);
        switch (transacao.getIdTipoTransacao()){
            case 1:
                tipoTransacaoLabel.setText("PIX");
                break;
            case 2:
                tipoTransacaoLabel.setText("DOC");
                break;
            case 3:
                tipoTransacaoLabel.setText("TED");
                break;
        }
    }

    public DestinatarioTransacao criaDestinoTransacao() throws IOException {
        if (codigoBancoTextField == null || agenciaTextField == null || numeroContaTextField == null) {
            Alerta.exibirAlertaErro("Erro na Transacao", "Nenhum campo pode ser nulo.");
        }
        return destinatarioTransacao = new DestinatarioTransacao(
                Integer.parseInt(codigoBancoTextField.getText()),
                Integer.parseInt(agenciaTextField.getText()),
                Integer.parseInt(numeroContaTextField.getText())
        );
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "tipoTransacao.fxml");
    }

    public void navegarValorTransacao(ActionEvent actionEvent) throws IOException {
        destinatarioTransacao = criaDestinoTransacao();
        DestinatarioTransacaoController.getInstance().setDestinatarioTransacao(destinatarioTransacao);
        System.out.println(DestinatarioTransacaoController.getInstance().getDestinatarioTransacao().toString());
        navegador.navegarPara(actionEvent, "valorTransacao.fxml");
    }
}
