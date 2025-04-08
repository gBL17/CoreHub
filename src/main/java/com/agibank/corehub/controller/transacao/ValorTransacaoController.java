package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.conta.ContaController;
import java.io.IOException;
import java.sql.SQLException;

import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.transacao.verificacao.VerificacaoTransacaoController;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.conta.ContaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ValorTransacaoController {
    public Transacao transacao;
    public DestinatarioTransacao destinatario;

    @FXML
    private TextField valor;

    @FXML
    private TextArea descricao;

    public void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 915);
        stage.setScene(scene);
        stage.show();
    }

    public void concluirTransacao(ActionEvent actionEvent) throws SQLException, IOException {
        IdContaDestinoController idContaDestinoController = new IdContaDestinoController();
        VerificacaoTransacaoController verificacaoTransacaoController = new VerificacaoTransacaoController();

        transacao.setValor(Double.parseDouble(valor.getText()));
        transacao.setDescricao(descricao.getText());
        transacao.setIdContaDestino(idContaDestinoController.buscarIdContaDestino(destinatario));

        verificacaoTransacaoController.verificarTransacao(transacao);
        Alerta.exibirAlertaSucesso("Transacao Cadastrada com sucesso!", "Aguardando verificação");
        navegarConta(actionEvent);
    }

    public String buscarSaldoConta(int idContaOrigem) throws SQLException {
        String saldoConta;
        ContaDAO contaDAO = new ContaDAO();
        saldoConta = contaDAO.buscarSaldoConta(idContaOrigem);
        contaDAO.fecharConexao();
        return saldoConta;
    }

    @FXML
    private void voltarTelaInicial(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800);
        stage.setScene(scene);
        stage.show();
    }
}
