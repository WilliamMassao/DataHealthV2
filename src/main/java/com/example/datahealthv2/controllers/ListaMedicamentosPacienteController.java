package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.UsuarioPaciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaMedicamentosPacienteController extends BaseController implements Initializable {

    UsuarioPaciente paciente = new UsuarioPaciente();
    ArrayList medicamentos = new ArrayList<>();
    ObservableList<Medicamento> obsList;

    @FXML
    private Label lblNomePaciente;

    @FXML
    private TableView<Medicamento> tbMedicamento = new TableView<>();

    @FXML
    private TableColumn<Medicamento, String> txtNomeComercial = new TableColumn<>();

    @FXML
    private TableColumn<Medicamento, String> txtNomeGenerico = new TableColumn<>();

    @FXML
    private TableColumn<Medicamento, String> txtLinkBula = new TableColumn<>();

    @FXML
    private TableColumn<Medicamento, String> txtDataPrescricao = new TableColumn<>();


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TESTEEE");
        addOnchageScreenListener(new onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                paciente = (UsuarioPaciente) objectData;
                lblNomePaciente.setText(paciente.getNome().split(" ")[0]);
                PacienteDAO pacienteDAO = new PacienteDAO();
                try {
                    medicamentos = pacienteDAO.RetornarMedicamentosCadastrados(paciente);
                    bindView();
                } catch (Exception e) {

                }

            }
        });

    }

    @FXML
    private void bindView() {
        convertList();
        txtNomeComercial.setCellValueFactory(
                new PropertyValueFactory<>("NomeComercial"));
        txtNomeGenerico.setCellValueFactory(
                new PropertyValueFactory<>("NomeGenerico"));
        txtLinkBula.setCellValueFactory(
                new PropertyValueFactory<>("LinkBula"));
        txtDataPrescricao.setCellValueFactory(
                new PropertyValueFactory<>("Data"));

        tbMedicamento.getItems().addAll(obsList);

    }

    @FXML
    private void convertList() {
        obsList = FXCollections.observableArrayList(medicamentos);
    }
}
