package com.agibank.corehub.controller.conta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.transacao.TipoTransacaoController;
import com.agibank.corehub.dao.conta.ContaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContaController {
    private int idConta;
    ContaDAO contaDAO = new ContaDAO();

    public ContaController() throws SQLException {
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
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

    public ArrayList<Conta> listarContaUsuario(int idUsuario) throws SQLException {

        return contaDAO.listarContasUsuario(idUsuario);

    }

    public void atualizarSaldo(int id_conta, double valor){
        try{
            contaDAO.atualizarSaldo(id_conta,valor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/tipoTransacao.fxml"));
        Parent root = loader.load();

        TipoTransacaoController tipoTransacaoController = loader.getController();
        tipoTransacaoController.setIdContaOrigem(idConta);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800);
        stage.setScene(scene);
        stage.show();
    }

}
