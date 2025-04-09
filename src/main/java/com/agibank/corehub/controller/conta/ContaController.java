package com.agibank.corehub.controller.conta;

import com.agibank.corehub.controller.ScoreController;
import com.agibank.corehub.controller.UsuarioController;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.conta.ContaDAO;
import java.io.IOException;
import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.beans.conta.Conta;
import com.agibank.corehub.controller.ContaLogadaController;
import com.agibank.corehub.controller.UsuarioLogadoController;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ContaController implements Initializable {
    private Usuario usarioLogado = UsuarioLogadoController.getInstance().getUsuario();
    private Conta contaLogada = ContaLogadaController.getInstance().getConta();
    private Navegador navegador = new Navegador();

    @FXML
    private Label saldoConta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            saldoConta.setText("R$ " + contaLogada.getSaldo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarSaldo(int id_conta, double valor){
        try{
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.atualizarSaldo(id_conta,valor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void navegarTipoTransacao(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "tipoTransacao.fxml");
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
                    double rendimento = contaPoupancaController.calcularRendimento(conta.getIdConta(),usuario.geUltimoAcesso());
                    atualizarSaldo(conta.getIdConta(),rendimento);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void navegarHome(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "home.fxml");
    }
}
