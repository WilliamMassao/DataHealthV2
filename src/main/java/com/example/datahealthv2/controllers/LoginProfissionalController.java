package com.example.datahealthv2.controllers;

import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class LoginProfissionalController extends BaseController {

    UsuarioProfissional user = new UsuarioProfissional();

    @FXML
    private TextField txtCPFProfissional;

    @FXML
    private PasswordField txtSenhaProfissional;

    @FXML
    void fazerLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String matricula = txtCPFProfissional.getText();
        String senha = txtSenhaProfissional.getText();
        realizarLogin(matricula, senha, "layout_home_logado_profissional.fxml",
                ("Tela de Logado Profissional " + matricula), event, user);
    }

    @FXML
    void voltarTelaAnterior(MouseEvent event) throws IOException, SQLException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_login.fxml", "Tela Home");
    }

}