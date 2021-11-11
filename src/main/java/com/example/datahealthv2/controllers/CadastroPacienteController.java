package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
import com.example.datahealthv2.conexao.DAO.usuario.ProfissionalDAO;
import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroPacienteController extends  BaseController{
    @FXML
    private TextField txtCpf, txtNome, txtTipoSanguineo, txtEmail, txtTelefone, txtSenha, txtConfirmarSenha;

    @FXML
    private ComboBox cbxMedicamento;

    @FXML
    private Button btnLogin;

    UsuarioProfissional profissional = new UsuarioProfissional();

    @FXML
    public void initialize() {
        addOnchageScreenListener(new BaseController.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                profissional = (UsuarioProfissional) objectData;
            }
        });
    }

    @FXML
    public void CadastrarPaciente(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Boolean dadosValidos = false;
        PacienteDAO paciente = new PacienteDAO();
        UsuarioPaciente userCreated = new UsuarioPaciente();

        userCreated.setCpf(txtCpf.getText());
        userCreated.setNome(txtNome.getText());
        userCreated.setTipoSanguineo(txtTipoSanguineo.getText());
        userCreated.setEmail(txtEmail.getText());
        userCreated.setTelefone(txtTelefone.getText());
        userCreated.setSenha(txtSenha.getText());

        String confirmarSenha = txtConfirmarSenha.getText();
        dadosValidos = ValidaDados(userCreated,confirmarSenha);

        if(dadosValidos){
            paciente.Inserir(userCreated);
            openAlert("Paciente Cadastrado", "Profissional Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        }
        else
            openAlert("Erro ao Cadastrar Paciente", "Por favor realize uma nova tentativa", "", Alert.AlertType.ERROR);
    }

    public boolean ValidaDados( UsuarioPaciente user, String confirmarSenha){
        Boolean dadosValidos = true;
        if(user.getNome().isEmpty() || user.getCpf().isEmpty() || user.getTipoSanguineo().isEmpty()
                || user.getEmail().isEmpty() || user.getTelefone().isEmpty() || user.getSenha().isEmpty() ||
                confirmarSenha.isEmpty()){
            dadosValidos = false;
        }
        if(!user.getSenha().equals(confirmarSenha))
            dadosValidos = false;

        return dadosValidos;
    }

    @FXML
    public void clickBackScreen(MouseEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_logado_profissional.fxml", "Home Profissional",profissional);
    }
}
