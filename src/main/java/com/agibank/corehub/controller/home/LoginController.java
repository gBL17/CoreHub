package com.agibank.corehub.controller.home;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.controller.utils.Alerta;
import com.agibank.corehub.controller.utils.CifradorSenha;
import com.agibank.corehub.controller.login.UsuarioLogadoController;
import com.agibank.corehub.dao.UsuarioDAO;
import com.agibank.corehub.controller.conta.ContaController;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            //Cria instancia do usuario logado
            UsuarioLogadoController.getInstance().setUsuario(usuarioVerificado);

            //Carrega as views na próxima lista
            ContaController contaController = new ContaController();
            contaController.atualizarContas(UsuarioLogadoController.getInstance().getUsuario().getId_Usuario());

            //Atualiza data de acesso
            usuarioDao.atualizarUltimaDataAcesso(LocalDate.now(),UsuarioLogadoController.getInstance().getUsuario().getId_Usuario());

            navegador.navegarPara(actionEvent, "home.fxml");
        } else {
            Alerta.exibirAlertaErro("Erro de Login", "Usuário ou Senha incorreta!");
        }
    }

    @FXML
    private void voltarTelaInicial(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent,"telaInicial.fxml");
    }
}
