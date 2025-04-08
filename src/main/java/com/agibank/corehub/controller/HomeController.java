package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeController implements Initializable {
    private static final Usuario usuarioLogado = UsuarioLogadoController.getInstance().getUsuario();
    private Navegador navegar = new Navegador();

    @FXML
    public Label labelNomeUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNomeUsuario.setText(usuarioLogado.getNome());
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        ContaLogadaController.getInstance().setConta(buscarConta(3));

        navegar.navegarPara(actionEvent, "conta.fxml");
    }

    public Conta buscarConta(int id) throws SQLException {
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscarConta(id);
        contaDAO.fecharConexao();
        return conta;
    }
}
