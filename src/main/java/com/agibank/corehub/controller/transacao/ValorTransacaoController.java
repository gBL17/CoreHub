package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import java.io.IOException;
import java.sql.SQLException;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.transacao.verificacao.VerificacaoTransacaoController;
import com.agibank.corehub.controller.utils.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ValorTransacaoController {
    public Transacao transacao;
    public DestinatarioTransacao destinatario;
    private final Navegador navegador = new Navegador();
    private final VerificacaoTransacaoController verificacaoTransacaoController = new VerificacaoTransacaoController();

    @FXML
    private TextField valor;

    @FXML
    private TextArea descricao;

    public void navegarContaTransacao(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "contaTransacao.fxml");
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        navegador.navegarPara(actionEvent, "conta.fxml");
    }

    public void preencherTransacao() throws SQLException {
        IdContaDestinoController idContaDestinoController = new IdContaDestinoController();
        transacao.setValor(Double.parseDouble(valor.getText()));
        transacao.setDescricao(descricao.getText());
        transacao.setIdContaDestino(idContaDestinoController.buscarIdContaDestino(destinatario));
    }

    public void concluirTransacao(ActionEvent actionEvent) throws SQLException, IOException {
        preencherTransacao();
        Alerta.exibirAlertaSucesso("Transacao Cadastrada com sucesso!", "Aguardando verificação");
        verificacaoTransacaoController.verificarTransacao(transacao);
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
