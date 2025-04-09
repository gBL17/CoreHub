package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.controller.Alerta;
import java.io.IOException;

import com.agibank.corehub.controller.utils.DestinatarioTransacaoController;
import com.agibank.corehub.controller.utils.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContaTransacaoController {
    private Navegador navegador = new Navegador();
    private DestinatarioTransacao destinatarioTransacao;

    @FXML
    private TextField codigoBancoTextField;

    @FXML
    private TextField agenciaTextField;

    @FXML
    private TextField numeroContaTextField;


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
