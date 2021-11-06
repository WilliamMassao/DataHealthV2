package com.example.datahealthv2.conexao.DAO.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

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
    public void Inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioPaciente paciente = (UsuarioPaciente)usuario;
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

    @Override
    protected String getLocalizaCommand() {
        return "select * from usuario_paciente where Cpf = ? ";
    }

    protected String getInserirPaciente() {
        return "insert into "+ tabela +" (Nome, Cpf, TipoSanguineo, Email, Telefone, Senha) values (?, ?, ?, ?, ?, ?)";
    }


}