package com.example.datahealthv2.controllers;

import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeProfissionalLogadoController {

    @FXML
    private Label lblNomeProfissional;

    BaseController base =  new BaseController();

    UsuarioProfissional user = new UsuarioProfissional();

    @FXML
    public void initialize() {
        base.addOnchageScreenListener(new BaseController.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                user = (UsuarioProfissional) objectData;
                lblNomeProfissional.setText(user.getNome().split(" ")[0]);
            }
        });

    }

    @FXML
    public void cadastrarPaciente(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        base.openNewScreen("layout_cadastro_paciente.fxml", "Cadastro Paciente");
    }

    @FXML
    public void cadastrarMedicamentos(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        base.openNewScreen("layout_cadastro_medicamentos.fxml", "Cadastro Medicamentos");
    }

    @FXML
    public void cadastrarProfissionais(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
    }
}
