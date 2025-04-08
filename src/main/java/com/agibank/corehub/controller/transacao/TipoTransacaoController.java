package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.ContaLogadaController;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TipoTransacaoController {

    private Transacao transacao = new Transacao();

    @FXML
    private StackPane pixButton;

    @FXML
    private StackPane docButton;

    @FXML
    private StackPane tedButton;

    public void setIdContaOrigem(int idContaOrigem) {
        transacao.setIdContaOrigem(idContaOrigem);
    }

    @FXML
    private void navegarParaTransacaoPix() throws IOException {
        transacao.setIdTipoTransacao(1); // PIX
        abrirTelaTransacao();
    }

    @FXML
    private void navegarParaTransacaoDoc() throws IOException {
        transacao.setIdTipoTransacao(2); // DOC
        abrirTelaTransacao();
    }

    @FXML
    private void navegarParaTransacaoTed() throws IOException {
        transacao.setIdTipoTransacao(3); // TED
        abrirTelaTransacao();
    }

    private void abrirTelaTransacao() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
        Parent root = loader.load();

        ContaTransacaoController contaTransacaoController = loader.getController();
        contaTransacaoController.transacao = transacao;

        Stage stage = (Stage) pixButton.getScene().getWindow(); // qualquer StackPane serve
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void navegarConta() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) pixButton.getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    private String buscarSaldoConta(int idContaOrigem) throws SQLException {
        String saldoConta;
        ContaDAO contaDAO = new ContaDAO();
        saldoConta = contaDAO.buscarSaldoConta(idContaOrigem);
        contaDAO.fecharConexao();
        return saldoConta;
    }
}
