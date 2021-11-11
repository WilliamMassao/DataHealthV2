package com.example.datahealthv2.controllers;

import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeProfissionalLogadoController extends BaseController{

    @FXML
    private Label lblNomeProfissional;


    UsuarioProfissional user = new UsuarioProfissional();

    @FXML
    public void initialize() {
        addOnchageScreenListener(new BaseController.onChangeScreen() {
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
        openNewScreen("layout_cadastro_paciente.fxml", "Cadastro Paciente",user);
    }

    @FXML
    public void cadastrarMedicamentos(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_cadastro_medicamentos.fxml", "Cadastro Medicamentos",user);
    }

    @FXML
    public void cadastrarProfissionais(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_cadastro_profissional.fxml", "Cadastro Profissional",user);
    }
    @FXML
    public void clickLogoff(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_login.fxml", "Tela Home");
    }
}
