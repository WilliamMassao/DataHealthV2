package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.MedicamentoDAO;
import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
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

public class ListaMedicamentosPacienteController extends BaseController {

    UsuarioPaciente paciente = new UsuarioPaciente();

    @FXML
    public void initialize() {
        addOnchageScreenListener(new onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                paciente = (UsuarioPaciente) objectData;
                PacienteDAO pacienteDAO = new PacienteDAO();
                try{
                    pacienteDAO.RetornarMedicamentosCadastrados(paciente);
                } catch (Exception e){

                }

            }
        });
    }

}
