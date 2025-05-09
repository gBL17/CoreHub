package com.agibank.corehub.controller.home;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.conta.ContaLogadaController;
import com.agibank.corehub.controller.login.UsuarioLogadoController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.agibank.corehub.controller.utils.Navegador;

import com.agibank.corehub.dao.conta.ContaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HomeController implements Initializable {
    private Usuario usuario = UsuarioLogadoController.getInstance().getUsuario();
    private Navegador navegar = new Navegador();
    public int idConta;

    @FXML
    public Label labelNomeUsuario;

    @FXML
    private ListView<String> listViewContas;

    private ObservableList<String> contasExibidas = FXCollections.observableArrayList();
    private ArrayList<Conta> contasUsuario = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelNomeUsuario.setText(usuario.getNome());
        try {
            carregarContasUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException {
        int selectedIndex = listViewContas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            System.out.println(contasUsuario.get(selectedIndex));
            ContaLogadaController.getInstance().setConta(contasUsuario.get(selectedIndex));
            navegar.navegarPara(actionEvent, "conta.fxml");
        } else {
            System.out.println("Nenhuma conta selecionada.");
        }
    }


    private void carregarContasUsuario() throws Exception {
        ContaDAO dao = new ContaDAO();
        contasUsuario = dao.listarContasUsuario(usuario.getId_Usuario());

        for (Conta conta : contasUsuario) {
            contasExibidas.add("Conta: " + conta.getTipo());
        }

        listViewContas.setItems(contasExibidas);
    }


}
