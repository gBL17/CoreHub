package com.agibank.corehub.controller.home;

import com.agibank.corehub.controller.conta.ContaController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class HomeController implements Initializable {
    private static final Usuario usuarioLogado = UsuarioLogadoController.getInstance().getUsuario();
public class HomeController implements Initializable {
    private int idUsuario;

    public void setIdUsuario(int id){
        this.idUsuario = id;
        try {
            carregarContasUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    @FXML
    public Label labelNomeUsuario;

    @FXML
    private ListView<String> listViewContas;

    private ObservableList<String> contasExibidas = FXCollections.observableArrayList();
    private ArrayList<Conta> contasUsuario = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarContasUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException {
        int selectedIndex = listViewContas.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Conta contaSelecionada = contasUsuario.get(selectedIndex);

            int idConta = 0;

            try {
                ContaDAO dao = new ContaDAO();
                idConta = dao.buscarIdContaPorAgenciaENumero(
                        contaSelecionada.getIdAgencia(), contaSelecionada.getNumero()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
            Parent root = loader.load();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNomeUsuario.setText(usuarioLogado.getNome());
    }

    public void navegarConta(ActionEvent actionEvent) throws IOException, SQLException {
        ContaLogadaController.getInstance().setConta(buscarConta(3));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agibank/corehub/views/conta.fxml"));
        Parent root = loader.load();

            ContaController controller = loader.getController();
            controller.setIdConta(idConta);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 412, 800);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Nenhuma conta selecionada.");
        }
    }


    private void carregarContasUsuario() throws Exception {
        ContaDAO dao = new ContaDAO();
        contasUsuario = dao.listarContasUsuario(idUsuario);

        for (Conta conta : contasUsuario) {
            contasExibidas.add("Conta: " + conta.getTipo());
        }

        listViewContas.setItems(contasExibidas);
    }



    public Conta buscarConta(int id) throws SQLException {
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscarConta(id);
        contaDAO.fecharConexao();
        return conta;
    }
}
