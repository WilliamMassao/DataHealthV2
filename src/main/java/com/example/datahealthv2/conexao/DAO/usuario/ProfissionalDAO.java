package com.example.datahealthv2.conexao.DAO.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.model.Entidade;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.Usuario;
import com.example.datahealthv2.model.UsuarioProfissional;

public class ProfissionalDAO <E extends Entidade> extends DAO {

    public ProfissionalDAO() {
        super(UsuarioProfissional.class);
        setTabela("usuario_profissional");

    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        UsuarioProfissional entidade = new UsuarioProfissional();

        try {
            entidade.setId(rs.getInt("Id"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setSenha(rs.getString("Senha"));
            entidade.setCpf(rs.getString("Cpf"));
            entidade.setCargo(rs.getString("Cargo"));
            entidade.setMatricula(rs.getString("Matricula"));
            entidade.setRegistroProfissional(rs.getString("RegistroProfissional"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (E)entidade;
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from usuario_profissional WHERE Cpf = ?";
    }

    @Override
    public void Inserir(Entidade e) throws SQLException, ClassNotFoundException {
        UsuarioProfissional profissional = (UsuarioProfissional)e;
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getInserirProfissional();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, profissional.getNome());
                stmt.setString(2, profissional.getCpf());
                stmt.setString(3, profissional.getSenha());
                stmt.setString(4, profissional.getMatricula());
                stmt.setString(5, profissional.getCargo());
                stmt.setString(6, profissional.getRegistroProfissional());
                stmt.executeUpdate();
            }
        }
    }

    protected String getInserirProfissional() {
        return "insert into "+ tabela +" (Nome, Cpf, Senha, Matricula, Cargo, RegistroProfissional) values (?, ?, ?, ?, ?, ?)";
    }

}