package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.App;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.VerificacaoSegurancaDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SegurancaTransacaoController {
    private Navegador navegador = new Navegador();
    private Stage stage = new Stage();

    public boolean verificarSegurancaTransacao(ActionEvent actionEvent, Transacao transacao) throws SQLException, IOException {
        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        if (verificarSeMaiorValorHistoricoTransacaoDaConta(transacao)){
            navegador.navegarPara(actionEvent, "verificacaoDeSeguranca.fxml");
            return false;
        }

        if (verificarHorarioInseguro(transacao) && verificarSeMaiorQueMediaValor(transacao)){
            navegador.navegarPara(actionEvent, "verificacaoDeSeguranca.fxml");
            return false;
        }

        statusTransacaoDAO.atualizarStatusTransacao(retornaIdTransacao(transacao), "APROVADA");
        return true;
    }

    //todo arrumar isso
    public boolean verificarHorarioInseguro(Transacao transacao) throws SQLException {
        VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
//        (verificacaoSegurancaDAO.transacaoCorreuHorarioSeguro(transacao.getId());
        return false;
    }

    public boolean verificarSeMaiorValorHistoricoTransacaoDaConta(Transacao transacao){
        double valorMinimoVerificacaoDeSeguranca = 500.0;
        try{
            VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
            double maiorTransferencia = verificacaoSegurancaDAO.maiorValorTransferenciaAprovada(transacao.getIdContaOrigem());

            if (maiorTransferencia < transacao.getValor() && maiorTransferencia > valorMinimoVerificacaoDeSeguranca){
                return true;
            }
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro No Banco de dados", e.getMessage());
        }
        return false;
    }

    public boolean verificarSeMaiorQueMediaValor(Transacao transacao) {
        double valorMinimoDeSeguranca = 100.0;
        try {
            VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
            double mediaTransferencia = verificacaoSegurancaDAO.mediaValoresTransacaoMaioresQueMinimo(transacao.getIdContaOrigem(), valorMinimoDeSeguranca);
            verificacaoSegurancaDAO.fecharConexao();
            if(mediaTransferencia < transacao.getValor()) {
                return true;
            }
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro No Banco de dados", e.getMessage());
        }
        return false;
    }

    public int retornaIdTransacao(Transacao transacao) throws SQLException{
        int idTransacao;
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        idTransacao = transacaoDAO.buscarIdTransacao(transacao);
        transacaoDAO.fecharConexao();
        return idTransacao;
    }

    private void carregarTelaSeguranca(){
        try {
            URL fxmlUrl = getClass().getResource("com/agibank/corehub/views/verificacaoDeSeguranca.fxml");
            if (fxmlUrl == null) {
                Alerta.exibirAlertaErro("Erro de Carregamento", "Arquivo de interface (verificacaoDeSeguranca.fxml) não encontrado.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load(); // Carregue o layout apenas uma vez
            Scene scene = new Scene(root); // Use dimensões automáticas baseadas no layout
            if (stage == null) {
                Alerta.exibirAlertaErro("Erro de Inicialização", "O 'stage' não foi inicializado.");
                return;
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alerta.exibirAlertaErro("Erro de Carregamento", "Ocorreu um erro ao carregar a tela: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
