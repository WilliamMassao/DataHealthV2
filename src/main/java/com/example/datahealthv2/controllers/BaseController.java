package com.example.datahealthv2.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.conexao.DAO.usuario.UsuarioDAOFactory;
import com.example.datahealthv2.model.UsuarioProfissional;
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


public class BaseController {

    UsuarioPaciente user = new UsuarioPaciente();

    Acesso acesso = new Acesso();

    UsuarioProfissional userProfissional = new UsuarioProfissional();

    public void openNewScreen(String fxml, String title, Object objectData) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/" + fxml));
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
        notifyAllListeners(title, objectData);
    }

    public void openNewScreen(String fxml, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/" + fxml));
        stage.setTitle(fxml);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
        notifyAllListeners(title, null);
    }


    public void openAlert(String title, String messageHeader, String messageInside, Alert.AlertType alertType) {
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

    public void clickLogoff(ActionEvent event) throws IOException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_login.fxml", "Tela Home");
    }

    // Enviar parâmetros para outras telas
    private static ArrayList<onChangeScreen> listeners = new ArrayList<>();

    public static interface onChangeScreen{
        void onScreenChanged(String newScreen, Object objectData);
    }

    public static void addOnchageScreenListener(onChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners (String newScreen, Object objectData){
        for (onChangeScreen l:listeners){
            l.onScreenChanged(newScreen, objectData);
        }
    }
}

