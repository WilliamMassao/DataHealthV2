package com.example.datahealthv2.conexao.DAO.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.model.Entidade;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.Usuario;
import com.example.datahealthv2.model.UsuarioPaciente;

public class PacienteDAO<E extends Entidade> extends DAO {

    public PacienteDAO() {
        super(UsuarioPaciente.class);
        setTabela("usuario_paciente");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        UsuarioPaciente entidade = new UsuarioPaciente();
        try {
            entidade.setId(rs.getInt("Id"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setSenha(rs.getString("Senha"));
            entidade.setCpf(rs.getString("Cpf"));
            entidade.setEmail(rs.getString("Email"));
            entidade.setTelefone(rs.getString("Telefone"));
            entidade.setTipoSanguineo(rs.getString("TipoSanguineo"));
            // entidade.setRemedios((ArrayList<Medicamento>) rs.getArray("Remedios"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public void Inserir(Entidade e) throws SQLException, ClassNotFoundException {
        UsuarioPaciente paciente = (UsuarioPaciente)e;
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getInserirPaciente();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getCpf());
                stmt.setString(3, paciente.getTipoSanguineo());
                stmt.setString(4, paciente.getEmail());
                stmt.setString(5, paciente.getTelefone());
                stmt.setString(6, paciente.getSenha());
                stmt.executeUpdate();
            }
        }
    }

    public void RetornarMedicamentosCadastrados(UsuarioPaciente paciente) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        ArrayList<Date> datas = new ArrayList<>();
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getRetornarMedimantos();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, Integer.toString(paciente.getId()));
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()){
                    Medicamento novoMedicamento = new Medicamento();
                    novoMedicamento.setId(Integer.parseInt(resultado.getString(2)));
                    novoMedicamento.setNomeComercial(resultado.getString(3));
                    novoMedicamento.setNomeGenerico(resultado.getString(4));
                    novoMedicamento.setLinkBula(resultado.getString(5));
                    medicamentos.add(novoMedicamento);
                }
            }
        }
    }

    public   void InserirPacienteMedicamento(Medicamento listaMedicamento) throws ClassNotFoundException, SQLException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getInserirMedicamentoPaciente();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setInt(1, CapturarIdUltimoPaciente() - 1);
                stmt.setInt(2, listaMedicamento.getId());
                stmt.setString(3, date);
                stmt.setString(4, "");
                stmt.setString(5, "");

                stmt.executeUpdate();
            }
        }
    }

    protected  Integer CapturarIdUltimoPaciente() throws SQLException, ClassNotFoundException {
        Integer id = 0;
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getLocalizarId();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    id = Integer.parseInt(resultado.getString(1));
                }
            }
        }
        return id;
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from usuario_paciente where Cpf = ? ";
    }

    protected String getInserirPaciente() {
        return "insert into "+ tabela +" (Nome, Cpf, TipoSanguineo, Email, Telefone, Senha) values (?, ?, ?, ?, ?, ?)";
    }
    protected String getInserirMedicamentoPaciente() {
        return "INSERT INTO `medicamento_paciente`(`IdPaciente`, `IdRemedio`, `DataPrescricao`, `Dosagem`, `Recorrencia`) values (?, ?, ?, ?, ?)";
    }

    protected String getLocalizarId(){
        return "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'datahealth_db' AND   TABLE_NAME   = 'usuario_paciente'";
    }

    protected  String getRetornarMedimantos(){
        return "SELECT Mp.IdPaciente, Mp.IdRemedio, m.NomeComercial, m.NomeGenerico, m.LinkBula, mp.DataPrescricao FROM `usuario_paciente` Up " +
                "INNER JOIN medicamento_paciente Mp on up.Id = Mp.IdPaciente " +
                "INNER JOIN medicamento m on m.Id = Mp.IdRemedio " +
                "Where Mp.IdPaciente = ? ";
    }

}