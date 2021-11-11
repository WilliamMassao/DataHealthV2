package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
import com.example.datahealthv2.conexao.DAO.usuario.ProfissionalDAO;
import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CadastroPacienteController extends  BaseController{
    @FXML
    private TextField txtCpf, txtNome, txtTipoSanguineo, txtEmail, txtTelefone, txtSenha, txtConfirmarSenha;

    @FXML
    private ComboBox cbxMedicamento;

    @FXML
    private Button btnLogin;

    public void CadastrarPaciente(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Boolean dadosValidos = false;
        PacienteDAO paciente = new PacienteDAO();
        UsuarioPaciente user = new UsuarioPaciente();

        user.setCpf(txtCpf.getText());
        user.setNome(txtNome.getText());
        user.setTipoSanguineo(txtTipoSanguineo.getText());
        user.setEmail(txtEmail.getText());
        user.setTelefone(txtTelefone.getText());
        user.setSenha(txtSenha.getText());
        String confirmarSenha = txtConfirmarSenha.getText();

        if(validarCampoVazio(user.getCpf(), "CPF") && validarCampoVazio(user.getNome(), "Nome") &&
                validarCampoVazio(user.getTipoSanguineo(), "Tipo Sanguíneo") && validarCampoVazio(user.getEmail(), "Email") &&
                validarCampoVazio(user.getTelefone(), "Telefone") && validarCampoVazio(user.getSenha(), "Senha")
                && validarCampoVazio(confirmarSenha, "Confirmação Senha") && validaSenhaConfirmacao(user.getSenha(), confirmarSenha) ){
            paciente.Inserir(user);
            openAlert("Paciente Cadastrado", "Profissional Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        }
    }
}
