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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class TipoTransacaoController {
    private Transacao transacao = new Transacao();

    @FXML
    private RadioButton radioPix;

    @FXML
    private RadioButton radioDoc;

    @FXML
    private RadioButton radioTed;

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        radioPix.setToggleGroup(group);
        radioDoc.setToggleGroup(group);
        radioTed.setToggleGroup(group);

        transacao.setIdContaOrigem(ContaLogadaController.getInstance().getConta().getIdConta());
    }

    public void setIdContaOrigem(int idContaOrigem){
        transacao.setIdContaOrigem(idContaOrigem);
    }

    private void setTipoTransacao() {
        if (!radioPix.isSelected() && !radioDoc.isSelected() && !radioTed.isSelected()) {
            Alerta.exibirAlertaErro("Erro na transação", "Selecione um meio de pagamento.");
            return;
        }
        if (radioPix.isSelected()) {
            transacao.setIdTipoTransacao(1);
        } else if (radioDoc.isSelected()) {
            transacao.setIdTipoTransacao(2);
        } else if (radioTed.isSelected()) {
            transacao.setIdTipoTransacao(3);
        }
    }

    public void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        setTipoTransacao();
        if (transacao.getIdTipoTransacao() != 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/contaTransacao.fxml"));
            Parent root = loader.load();

            ContaTransacaoController contaTransacaoController = loader.getController();
            contaTransacaoController.transacao = transacao;

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 412, 915);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
