package com.example.datahealthv2.controllers;

import java.io.IOException;
import java.sql.SQLException;

import com.example.datahealthv2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.datahealthv2.login.Acesso;
import com.example.datahealthv2.model.UsuarioPaciente;


public class LoginPacienteController extends BaseController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtCPFPaciente;

    @FXML
    private PasswordField txtSenhaPaciente;

    @FXML
    void fazerLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String cpfPaciente = txtCPFPaciente.getText();
        String senhaPaciente = txtSenhaPaciente.getText();
        realizarLogin(cpfPaciente, senhaPaciente, "layout_lista_medicamentos_cadastrados_para_nomePessoa.fxml",
                ("Tela de Medicamentos cadastrados para " + cpfPaciente), event, user);
    }

    @FXML
    void voltarTelaAnterior(MouseEvent event) throws IOException, SQLException {
        (((Node) event.getSource())).getScene().getWindow().hide();
       openNewScreen("layout_home_login.fxml", "Tela Home");
    }



}