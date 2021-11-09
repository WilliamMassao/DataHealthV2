package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
import com.example.datahealthv2.conexao.DAO.usuario.ProfissionalDAO;
import com.example.datahealthv2.login.Acesso;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CadastroProfissionalController {

    @FXML
    private TextField txtCpf,txtNome,txtMatricula,txtCargo,txtSenha,txtRegistroProfissional,txtConfirmarSenha;

    @FXML
    private Button btnLogin;

    @FXML
    public void CadastrarProfissional(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProfissionalDAO profissional = new ProfissionalDAO();
        Boolean dadosValidos = false;

        UsuarioProfissional user = new UsuarioProfissional();
        user.setCpf(txtCpf.getText());
        user.setCargo(txtCargo.getText());
        user.setRegistroProfissional(txtRegistroProfissional.getText());
        user.setMatricula(txtCargo.getText());
        user.setSenha(txtSenha.getText());
        user.setNome(txtNome.getText());
        user.setMatricula(txtMatricula.getText());

        String confirmarSenha = txtConfirmarSenha.getText();
        dadosValidos = ValidaDados(user,confirmarSenha);

        if(dadosValidos){
            profissional.Inserir(user);
            openAlert("Profissional Cadastrado", "Profissional Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        }
        else
        openAlert("Erro ao Cadastrar Profissional", "Por favor realize uma nova tentativa", "", Alert.AlertType.ERROR);
    }

    public boolean ValidaDados( UsuarioProfissional user, String confirmarSenha){
        Boolean dadosValidos = true;
        if(user.getNome().isEmpty() || user.getCargo().isEmpty() || user.getRegistroProfissional().isEmpty()
                || user.getMatricula().isEmpty() || user.getSenha().isEmpty() || user.getCpf().isEmpty()
                || confirmarSenha.isEmpty()){
            dadosValidos = false;
        }
        if(!user.getSenha().equals(confirmarSenha))
            dadosValidos = false;

        return dadosValidos;
    }

    public void openAlert(String title, String messageHeader, String messageInside, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(messageHeader);
        alert.setContentText(messageInside);
        alert.showAndWait();
    }
}
