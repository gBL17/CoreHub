package com.agibank.corehub.controller;

import com.agibank.corehub.controller.conta.ContaController;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.io.IOException;
import java.sql.SQLException;
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

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println(getIdUsuario());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        ContaController contaController = loader.getController();
        contaController.setIdConta(3);
        contaController.setSaldoConta(buscarSaldoConta(3));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    private String buscarSaldoConta(int idConta) throws SQLException {
        String saldoConta;
        ContaDAO contaDAO = new ContaDAO();
        saldoConta = contaDAO.buscarSaldoConta(idConta);
        contaDAO.fecharConexao();
        return saldoConta;
    }
}
