package com.example.datahealthv2.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeLoginController {

    BaseController base = new BaseController();

    private Stage stage;
    private Scene scene;
    private Parent root;

    // @FXML
    // private Button btnConvidado;

    // @FXML
    // private Button btnProfissional;

    @FXML
    void switchToLoginPaciente(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        base.openNewScreen("layout_login_paciente.fxml", "Tela de Login Paciente");
    }

    @FXML
    void switchToLoginProfissional(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        base.openNewScreen("layout_login_profissional.fxml", "Tela de Login Profissional");
    }
}
