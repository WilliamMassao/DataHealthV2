package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.MedicamentoDAO;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroMedicamentoController extends BaseController {

    UsuarioProfissional profissional = new UsuarioProfissional();

    @FXML
    public void initialize() {
        addOnchageScreenListener(new BaseController.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                profissional = (UsuarioProfissional) objectData;
            }
        });
    }

    @FXML
    private TextField txtNomeComercial, txtLinkBula, txtNomeGenerico;

    public void CadastrarMedicamento(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Boolean dadosValidos = false;
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

        Medicamento medicamento = new Medicamento();
        medicamento.setLinkBula(txtLinkBula.getText());
        medicamento.setNomeComercial(txtNomeComercial.getText());
        medicamento.setNomeGenerico(txtNomeGenerico.getText());

        if (dadosValidos) {
            medicamentoDAO.Inserir(medicamento);
            openAlert("Paciente Cadastrado", "Profissional Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        } else
            openAlert("Erro ao Cadastrar Paciente", "Por favor realize uma nova tentativa", "", Alert.AlertType.ERROR);
    }

    public void openAlert(String title, String messageHeader, String messageInside, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(messageHeader);
        alert.setContentText(messageInside);
        alert.showAndWait();
    }

    public boolean ValidaDados(UsuarioPaciente user, String confirmarSenha) {
        Boolean dadosValidos = true;
        return dadosValidos;
    }

    @FXML
    public void clickBackScreen(MouseEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_logado_profissional.fxml", "Home Profissional", profissional);
    }

}
