package com.agibank.corehub.controller.home;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.CifradorSenha;
import com.agibank.corehub.controller.UsuarioLogadoController;
import com.agibank.corehub.dao.UsuarioDAO;
import com.agibank.corehub.controller.conta.ContaController;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    Navegador navegador = new Navegador();

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
            Alerta.exibirAlertaErro("Erro de Login", "Usuario não pode ser vazio!");
        }

        if (senha == null) {
            Alerta.exibirAlertaErro("Erro de Login", "Senha não pode ser vazia!");
        }

        if(usuarioVerificado != null && cifrador.validarSenhaCrifrada(usuarioVerificado.getSenha(),senha)){
            UsuarioLogadoController.getInstance().setUsuario(usuarioVerificado);
            ContaController contaController = new ContaController();
            contaController.atualizarContas(UsuarioLogadoController.getInstance().getUsuario().getId_Usuario());
            navegador.navegarPara(actionEvent, "home.fxml");
        } else {
            Alerta.exibirAlertaErro("Erro de Login", "Usuário ou Senha incorreta!");
        }
    }


    public void navegarHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/home/home.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();
        homeController.labelNomeUsuario.setText(usuario.getNome());
        homeController.setIdUsuario(usuario.getId_Usuario());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void voltarTelaInicial(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/telainicial.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800); // mantém o mesmo tamanho
        stage.setScene(scene);
        stage.show();
    }
}
