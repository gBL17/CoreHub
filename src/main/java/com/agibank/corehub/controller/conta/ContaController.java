package com.agibank.corehub.controller.conta;

import com.agibank.corehub.controller.ScoreController;
import com.agibank.corehub.controller.UsuarioController;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.io.IOException;
import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.ContaLogadaController;
import com.agibank.corehub.controller.UsuarioLogadoController;
import com.agibank.corehub.controller.transacao.TipoTransacaoController;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ContaController implements Initializable {
    private static final Usuario usarioLogado = UsuarioLogadoController.getInstance().getUsuario();
    private static Conta contaLogada;

    @FXML
    private Label saldoConta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         contaLogada = ContaLogadaController.getInstance().getConta();
         saldoConta.setText(String.valueOf(contaLogada.getSaldo()));
    }

    //    public Conta buscarContaPorId(int id_conta){
//        try{
//            return contaDAO.buscarConta(id_conta);
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }

//        return null;
//    }
//    public int tratamento(int idUsuario) throws SQLException {
//        contaDAO.listarContasUsuario(idUsuario);

//        return 1;
//    }
//    public ArrayList<Conta> listarContaUsuario(int idUsuario) throws SQLException {
//
//        return contaDAO.listarContasUsuario(idUsuario);

//
//    }

    public void atualizarSaldo(int id_conta, double valor){
        try{
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.atualizarSaldo(id_conta,valor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/tipoTransacao.fxml"));
        Parent root = loader.load();

        TipoTransacaoController tipoTransacaoController = loader.getController();
        tipoTransacaoController.setIdContaOrigem(contaLogada.getIdConta());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 412, 800);
        stage.setScene(scene);
        stage.show();
    }


    public void atualizarContas(int id_usuario) throws SQLException{
        ScoreController scoreController = new ScoreController();
        ContaPoupancaController contaPoupancaController = new ContaPoupancaController();
        ContaCorrenteController contaCorrenteController = new ContaCorrenteController();
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.buscarDadosUsuario(id_usuario);
        ArrayList<Conta> contas;

        try{
            ContaDAO contaDAO = new ContaDAO();
            contas = contaDAO.listarContasUsuario(id_usuario);

            for (Conta conta : contas){
                LocalDate dataAbertura = Instant.ofEpochMilli(conta.getDataAbertura().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                scoreController.atualizarScore(conta.getIdConta(),usuario.geUltimoAcesso(),dataAbertura);
                if(conta.getIdTipo() == 1){
                    contaCorrenteController.descontarSaldoContaCorrente(conta.getIdConta(),usuario.geUltimoAcesso());
                }else if (conta.getIdTipo() == 2){
                    System.out.println();
                    double rendimento = contaPoupancaController.calcularRendimento(conta.getIdConta(), usuario.geUltimoAcesso());
                    atualizarSaldo(conta.getIdConta(),rendimento);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
