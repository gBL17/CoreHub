package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import java.io.IOException;
import java.sql.SQLException;

import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.dao.TransacaoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ValorTransacaoController {
    public Transacao transacao;
    public DestinatarioTransacao destinatario;

    @FXML
    private TextField valor;

    @FXML
    private TextArea descricao;

    public void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public void concluirTransacao(ActionEvent actionEvent) throws SQLException, IOException {
        transacao.setValor(Double.parseDouble(valor.getText()));
        transacao.setDescricao(descricao.getText());

        System.out.println(transacao);
        //todo procurar idContaDestino
        //todo adicionar idContaDestino a transação
        //todo cadastrar trasação
        //todo enviar transação para verificação

        Alerta.exibirAlertaErro("Transacao Concluída", "Parabéns");
        navegarConta(actionEvent);
    }
}
