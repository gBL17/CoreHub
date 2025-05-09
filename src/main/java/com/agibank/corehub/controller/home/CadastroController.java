package com.agibank.corehub.controller.home;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.controller.utils.Alerta;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController {
    private Navegador navegador = new Navegador();
    @FXML
    public TextField campoNome;
    @FXML
    public TextField campoSobrenome;
    @FXML
    public TextField campoApelido;
    @FXML
    public TextField campoEmail;
    @FXML
    public TextField campoTelefone;
    @FXML
    public TextField campoCPF;
    @FXML
    public TextField campoDataNascimento;
    @FXML
    public TextField campoRua;
    @FXML
    public TextField campoNumero;
    @FXML
    public TextField campoComplemento;
    @FXML
    public PasswordField campoSenha;
    @FXML
    public PasswordField campoConfirmarSenha;
    @FXML
    public Button btnCadastrar;

    public void voltarTelaInicial(ActionEvent actionEvent) throws IOException {
        navegador.navegarPara(actionEvent, "home.fxml");
    }

    public void cadastrarUsuario(ActionEvent actionEvent) {
        if (campoSenha.getText().equals(campoConfirmarSenha.getText())) {
            try {
                String telefoneLimpo = campoTelefone.getText().replaceAll("\\D", "");

                if (telefoneLimpo.length() > 11) {
                    Alerta.exibirAlertaErro("Telefone inválido", "O número de telefone deve ter até 9 dígitos.");
                    return;
                }

                int telefone = Integer.parseInt(telefoneLimpo);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario novoUsuario = new Usuario(
                        campoNome.getText(),
                        campoApelido.getText(),
                        campoSenha.getText(),
                        campoEmail.getText(),
                        telefone,
                        converterTextoParaDataSQL(campoDataNascimento.getText()).toLocalDate(),
                        campoRua.getText(),
                        Integer.parseInt(campoNumero.getText()),
                        campoComplemento.getText(),
                        LocalDate.now()
                );

                if (usuarioDAO.criarUsuario(novoUsuario) == 1) {
                    navegador.navegarPara(actionEvent, "telaInicial.fxml");
                    Alerta.exibirAlertaSucesso("Cadastro Realizado", "Cadastro realizado com sucesso!");
                } else {
                    Alerta.exibirAlertaErro("O Cadastro falhou", "Tente novamente");
                }

            } catch (NumberFormatException e) {
                Alerta.exibirAlertaErro("Erro de Formato", "Telefone ou número inválido. Digite apenas números.");
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alerta.exibirAlertaErro("Senhas diferentes", "As senhas informadas não são iguais.");
        }
    }


    public static Date converterTextoParaDataSQL(String dataTexto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(dataTexto, formatter);
        return Date.valueOf(dataFormatada);
    }

}
