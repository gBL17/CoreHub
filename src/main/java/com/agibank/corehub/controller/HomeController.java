package com.agibank.corehub.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController {
    private int idUsuario;

    public void setIdUsuario(int id){
        this.idUsuario = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    @FXML
    public Label labelNomeUsuario;

    public void navegarConta(ActionEvent actionEvent) throws IOException {
        System.out.println(getIdUsuario());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        ContaController controller = loader.getController();
        controller.setIdConta(26);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }
}
