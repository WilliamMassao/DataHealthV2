package com.example.datahealthv2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.SQLException;

public class HomeLoginController extends BaseController {


    @FXML
    void switchToLoginPaciente(ActionEvent event) throws IOException, SQLException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        clearAllListeners();
        openNewScreen("layout_login_paciente.fxml", "Tela de Login Paciente", null);
    }

    @FXML
    void switchToLoginProfissional(ActionEvent event) throws IOException, SQLException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        clearAllListeners();
        openNewScreen("layout_login_profissional.fxml", "Tela de Login Profissional", null);
    }
}
