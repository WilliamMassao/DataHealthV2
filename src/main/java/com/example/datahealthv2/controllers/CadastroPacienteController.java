package com.example.datahealthv2.controllers;

import com.example.datahealthv2.conexao.DAO.usuario.MedicamentoDAO;
import com.example.datahealthv2.conexao.DAO.usuario.PacienteDAO;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroPacienteController extends  BaseController{
    @FXML
    private TextField txtCpf, txtNome, txtTipoSanguineo, txtEmail, txtTelefone, txtSenha, txtConfirmarSenha;

//    @FXML
//    private ComboBox<String> cbxMedicamento;
    @FXML
    private CheckComboBox cbxMedicamento;

    private ObservableList<String> obsList;



    @FXML
    private Button btnLogin;

    UsuarioProfissional profissional = new UsuarioProfissional();
    ArrayList<Medicamento> medicamentos = new ArrayList<>();
    ArrayList<String> nomeMedicamentos = new ArrayList<>();
    MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @FXML
    public void initialize() {
        addOnchageScreenListener(new BaseController.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object objectData) {
                profissional = (UsuarioProfissional) objectData;
                try {
                    medicamentos = getMedicamentos();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (Medicamento medicamento:medicamentos) {
                    nomeMedicamentos.add(medicamento.getNomeGenerico());
                }
                obsList = FXCollections.observableArrayList(nomeMedicamentos);
                cbxMedicamento.getItems().addAll(obsList);
            }
        });
    }

    private ArrayList<Medicamento> getMedicamentos() throws SQLException {
        return medicamentoDAO.lista();
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

        if(validarCampoVazio(userCreated.getCpf(), "CPF") && validarCampoVazio(userCreated.getNome(), "Nome") &&
                validarCampoVazio(userCreated.getTipoSanguineo(), "Tipo Sanguíneo") && validarCampoVazio(userCreated.getEmail(), "Email") &&
                validarCampoVazio(userCreated.getTelefone(), "Telefone") && validarCampoVazio(userCreated.getSenha(), "Senha")
                && validarCampoVazio(confirmarSenha, "Confirmação Senha") && validaSenhaConfirmacao(userCreated.getSenha(), confirmarSenha) && validarCPF(userCreated.getCpf())
                && validarCPFExistente(userCreated)){
            userCreated.setCpf(txtCpf.getText().replaceAll("\\.", "").replaceAll("-", ""));
            paciente.Inserir(userCreated);
            paciente.InserirPacienteMedicamento((Medicamento) cbxMedicamento.getCheckModel().getCheckedItems());
            openAlert("Paciente Cadastrado", "Profissional Cadastrado com Sucesso!", "", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    public void clickBackScreen(MouseEvent event) throws IOException, SQLException {
        (((Node) event.getSource())).getScene().getWindow().hide();
        openNewScreen("layout_home_logado_profissional.fxml", "Home Profissional",profissional);
    }
}
