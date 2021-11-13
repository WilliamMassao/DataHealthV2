package com.example.datahealthv2.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.conexao.DAO.usuario.UsuarioDAOFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.datahealthv2.login.Acesso;
import com.example.datahealthv2.model.Usuario;
import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;


public class BaseController {

    UsuarioPaciente user = new UsuarioPaciente();

    static Acesso acesso = new Acesso();

    UsuarioProfissional userProfissional = new UsuarioProfissional();

    public void openNewScreen(String fxml, String title, Object objectData) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/" + fxml));
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
        notifyAllListeners(title, objectData);
    }

    public void openNewScreen(String fxml, String title) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/" + fxml));
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
        notifyAllListeners(title, null);
    }


    public static void openAlert(String title, String messageHeader, String messageInside, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(messageHeader);
        alert.setContentText(messageInside);
        alert.showAndWait();
    }

    public void realizarLogin(String login, String senha, String fxml, String nextTitle, ActionEvent event, Usuario user) throws ClassNotFoundException, SQLException, IOException{
        user.setCpf(login);
        user.setSenha(senha);
        Boolean senhaValida = acesso.validaUsuario(user);

        if(senhaValida.equals(true)){
            ((Node)(event.getSource())).getScene().getWindow().hide();
            openNewScreen(fxml, nextTitle, acesso.retornaUsuario(user));
        }
        else{
            openAlert("Erro ao Realizar Login", "Por favor realize uma nova tentativa", "", AlertType.ERROR);
        }

    }

    public void clickLogoff(ActionEvent event) throws IOException, SQLException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_login.fxml", "Tela Home");
    }

    // Enviar parâmetros para outras telas
    private static ArrayList<onChangeScreen> listeners = new ArrayList<>();

    public static interface onChangeScreen{
        void onScreenChanged(String newScreen, Object objectData) throws SQLException;
    }

    public static void addOnchageScreenListener(onChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners (String newScreen, Object objectData) throws SQLException {
        for (onChangeScreen l:listeners){
            l.onScreenChanged(newScreen, objectData);
        }
    }

    // Validações dos Campos
    public Boolean validarCampoVazio(Object objectData, String nomeCampo){
        Boolean campoVazio = true;
        if(objectData.toString().isEmpty()){
            campoVazio = false;
            openAlert("Erro ao Cadastrar Paciente", "Por favor, insira um valor no campo " + nomeCampo, "", Alert.AlertType.ERROR);
        }
        return  campoVazio;
    }

    public Boolean validaSenhaConfirmacao(String senha, String senhaConfirmacao){
        Boolean senhaConfirmada = true;
        if(!senha.equals(senhaConfirmacao)){
            senhaConfirmada = false;
            openAlert("Erro ao Cadastrar Paciente", "Senhas divergem, por favor cadastre a mesma senha", "", Alert.AlertType.ERROR);
        }
        return  senhaConfirmada;
    }

    public static boolean validarCPF (String cpf)
    {
        String regex = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
        Pattern p = Pattern.compile(regex);
        if (cpf == null) {
            return false;
        }
        Matcher m = p.matcher(cpf);
        if(!m.matches()){
            openAlert("Erro ao Cadastrar Paciente", "CPF Inválido", "", Alert.AlertType.ERROR);
        }
        return m.matches();
    }

    public static boolean validarCPFExistente (Usuario user){
        boolean cpfNaoExistente = true;
        user.setCpf(user.getCpf().replaceAll("\\.", "").replaceAll("-", ""));
        try {
             if (!acesso.retornaUsuario(user).getCpf().equals("")){
                 openAlert("Erro ao Cadastrar Paciente", "CPF já cadastrado", "", Alert.AlertType.ERROR);
                 cpfNaoExistente = false;
             }
        }catch (Exception e){
            cpfNaoExistente = true;
        }
        return cpfNaoExistente;
    }
}

