package com.agibank.corehub.controller.home;

import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.conta.ContaController;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    private int idUsuario;
    private Navegador navegar = new Navegador();

    public void setIdUsuario(int id){
        this.idUsuario = id;
        try {
            carregarContasUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    @FXML
    public Label labelNomeUsuario;

    @FXML
    private ListView<String> listViewContas;

    private ObservableList<String> contasExibidas = FXCollections.observableArrayList();
    private ArrayList<Conta> contasUsuario = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarContasUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException {
        int selectedIndex = listViewContas.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Conta contaSelecionada = contasUsuario.get(selectedIndex);

            int idConta = 0;

            try {
                ContaDAO dao = new ContaDAO();
                idConta = dao.buscarIdContaPorAgenciaENumero(
                        contaSelecionada.getIdAgencia(), contaSelecionada.getNumero()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            navegar.navegarPara(actionEvent, "conta.fxml");
        } else {
            System.out.println("Nenhuma conta selecionada.");
        }
    }


    private void carregarContasUsuario() throws Exception {
        ContaDAO dao = new ContaDAO();
        contasUsuario = dao.listarContasUsuario(idUsuario);

        for (Conta conta : contasUsuario) {
            contasExibidas.add("Conta: " + conta.getTipo());
        }

        listViewContas.setItems(contasExibidas);
    }


}
