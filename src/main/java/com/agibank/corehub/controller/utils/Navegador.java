package com.agibank.corehub.controller.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Navegador {
    public void navegarPara(ActionEvent actionEvent, String nomeViewFXML) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/"+nomeViewFXML));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800);
        stage.setScene(scene);
        stage.show();
    }
}
