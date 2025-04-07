package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.conta.ContaController;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    private static final Usuario usuarioLogado = UsuarioLogadoController.getInstance().getUsuario();

    @FXML
    public Label labelNomeUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNomeUsuario.setText(usuarioLogado.getNome());
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        ContaLogadaController.getInstance().setConta(buscarConta(3));

        //cuidado isso é necessário para não dar nullpointer na view de Conta e eu não sei o motivo
        ContaController contaController = loader.getController();
        contaController.setSaldoConta(String.valueOf(ContaLogadaController.getInstance().getConta().getSaldo()));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public Conta buscarConta(int id) throws SQLException {
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscarConta(id);
        contaDAO.fecharConexao();
        return conta;
    }
}
