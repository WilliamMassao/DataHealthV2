package com.example.datahealthv2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.io.IOException;

public class HomeProfissionalLogadoController {

    BaseController base = new BaseController();

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
        base.openNewScreen("layout_cadastro_profissional.fxml", "Cadastro Profissional");
    }
}
