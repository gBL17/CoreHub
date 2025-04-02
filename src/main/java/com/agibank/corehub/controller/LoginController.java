package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.dao.UsuarioDAO;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField campoApelido;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private void login(ActionEvent actionEvent) throws IOException, SQLException {
        String usuario = campoApelido.getText();
        String senha = campoSenha.getText();

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuarioVerificado = usuarioDao.buscarUsuarioApelido(usuario);
        CifradorSenha cifrador = new CifradorSenha();

        if (usuario == null) {
            Alerta.exibirAlerta("Erro de Login", "Usuario não pode ser vazio!");
        }

        if (senha == null) {
            Alerta.exibirAlerta("Erro de Login", "Senha não pode ser vazia!");
        }

        if(usuarioVerificado != null && cifrador.validarSenhaCrifrada(usuarioVerificado.getSenha(),senha)){
            navegarHome(actionEvent, usuarioVerificado);
        } else {
            Alerta.exibirAlerta("Erro de Login", "Usuário ou Senha incorreta!");
        }
    }


    public void navegarHome(ActionEvent actionEvent, Usuario usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/home.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.labelNomeUsuario.setText(usuario.getNome());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }
}
