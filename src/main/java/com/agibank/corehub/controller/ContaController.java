package com.agibank.corehub.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.agibank.corehub.beans.conta.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.agibank.corehub.dao.conta.ContaDAO;

public class ContaController {
    ContaDAO contaDAO = new ContaDAO();

    public ContaController() throws SQLException {
    }

    public void navegarTipoTransacao(ActionEvent actionEvent, String nomeDaTela) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/tipoTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public Conta buscarContaPorId(int id_conta){
        try{
            return contaDAO.buscarConta(id_conta);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int tratamento(int idUsuario) throws SQLException {
        contaDAO.listarContasUsuario(idUsuario);
        return 1;
    }

    public int listarContaUsuario(int idUsuario) throws SQLException {
        tratamento(idUsuario);
        return 1;
    }

    public void atualizarSaldo(int id_conta, double valor){
        try{
            contaDAO.atualizarSaldo(id_conta,valor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
