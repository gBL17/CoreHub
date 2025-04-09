package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.beans.transacao.DestinatarioTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import java.io.IOException;
import java.sql.SQLException;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.ContaLogadaController;
import com.agibank.corehub.controller.transacao.verificacao.ConfirmarTransacao;
import com.agibank.corehub.controller.utils.DestinatarioTransacaoController;
import com.agibank.corehub.controller.utils.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ValorTransacaoController {
    private Conta contaLogada = ContaLogadaController.getInstance().getConta();
    public Transacao transacao = TransacaoController.getInstance().getTransacao();
    public DestinatarioTransacao destinatario = DestinatarioTransacaoController.getInstance().getDestinatarioTransacao();
    private final Navegador navegador = new Navegador();
    private final ConfirmarTransacao verificacaoTransacaoController = new ConfirmarTransacao();

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
        IdContaDestinoController idContaDestinoController = new IdContaDestinoController(destinatario);
        transacao.setIdContaOrigem(contaLogada.getIdConta());
        transacao.setValor(Double.parseDouble(valor.getText()));
        transacao.setDescricao(descricao.getText());
        transacao.setIdContaDestino(idContaDestinoController.buscarIdContaDestino(destinatario));
    }

    public void concluirTransacao(ActionEvent actionEvent) throws SQLException, IOException {
        preencherTransacao();
        verificacaoTransacaoController.confirmarTransacao(actionEvent, transacao);
    }
}
