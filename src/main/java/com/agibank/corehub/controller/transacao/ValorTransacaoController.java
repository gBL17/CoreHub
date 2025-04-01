package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ValorTransacaoController {
    public Transacao transacao;
    public DestinatarioTransacao destinatario;

    public void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        System.out.println(transacao);
        System.out.println(destinatario);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }
}
