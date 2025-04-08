package com.agibank.corehub.controller.transacao.verificacao;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class VerificaoDeSegurancaController {

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
}


