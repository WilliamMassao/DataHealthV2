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

        if (validarCampoVazio(medicamento.getLinkBula(), "Link da Bula")
                && validarCampoVazio(medicamento.getNomeComercial(), "Nome Comercial do Medicamento")
                && validarCampoVazio(medicamento.getNomeGenerico(), "Nome Genérico do Medicamento")) {
            medicamentoDAO.Inserir(medicamento);
            openAlert("Medicamento Cadastrado", "Medicamento Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void clickBackScreen(MouseEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_logado_profissional.fxml", "Home Profissional", profissional);
    }

}
