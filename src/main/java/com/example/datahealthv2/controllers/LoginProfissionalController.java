package com.example.datahealthv2.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.datahealthv2.model.UsuarioProfissional;

public class LoginProfissionalController {

    BaseController base = new BaseController();

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtCPFProfissional;

    @FXML
    private TextField txtSenhaProfissional;

    @FXML
    void fazerLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        UsuarioProfissional user = new UsuarioProfissional();
        String matricula = txtCPFProfissional.getText();
        String senha = txtSenhaProfissional.getText();
        base.realizarLogin(matricula, senha, "layout_home_logado_profissional.fxml",
                ("Tela de Logado Profissional " + matricula), event, user);
    }

    @FXML
    void voltarTelaAnterior(MouseEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        base.openNewScreen("layout_home_login.fxml", "Tela Home");
    }


}