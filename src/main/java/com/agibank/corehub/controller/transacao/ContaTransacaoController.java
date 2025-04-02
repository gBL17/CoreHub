package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContaTransacaoController {
    public Transacao transacao;
    public DestinatarioTransacao destinatario = new DestinatarioTransacao();

    @FXML
    private TextField codigoBancoTextField;

    @FXML
    private TextField agenciaTextField;

    @FXML
    private TextField numeroContaTextField;

    public void setDestinoTransacao() {
        if (codigoBancoTextField == null || agenciaTextField == null || numeroContaTextField == null) {
            Alerta.exibirAlerta("Erro na Transacao", "Nenhum campo pode ser nulo.");
            return;
        }
        transacao.setTransferenciaExterna(!Objects.equals(codigoBancoTextField.getText(), "121"));
        destinatario.setCodigoBanco(codigoBancoTextField.getText());
        destinatario.setAgencia(agenciaTextField.getText());
        destinatario.setNumero(numeroContaTextField.getText());
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/tipoTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public void navegarValorTransacao(ActionEvent actionEvent) throws IOException {
        setDestinoTransacao();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/valorTransacao.fxml"));
        Parent root = loader.load();

        ValorTransacaoController valorTransacaoController = loader.getController();
        valorTransacaoController.transacao = transacao;
        valorTransacaoController.destinatario = destinatario;

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }
}
