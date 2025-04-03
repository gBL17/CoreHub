package com.agibank.corehub.controller;

import java.io.IOException;

import com.agibank.corehub.controller.transacao.TipoTransacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContaController {
    private int idConta;

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/tipoTransacao.fxml"));
        Parent root = loader.load();

        TipoTransacaoController tipoTransacaoController = loader.getController();
        tipoTransacaoController.setIdContaOrigem(idConta);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }
}
