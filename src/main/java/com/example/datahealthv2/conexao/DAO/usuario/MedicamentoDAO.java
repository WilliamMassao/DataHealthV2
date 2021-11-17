package com.example.datahealthv2.conexao.DAO.usuario;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.model.Entidade;
import com.example.datahealthv2.model.Medicamento;

import java.sql.*;

public class MedicamentoDAO<E extends Entidade> extends DAO {

    public MedicamentoDAO() {
        super(Medicamento.class);
        setTabela("medicamento");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        Medicamento entidade = new Medicamento();
        try {
            entidade.setId(rs.getInt("Id"));
            entidade.setNomeComercial(rs.getString("NomeComercial"));
            entidade.setNomeGenerico(rs.getString("NomeGenerico"));
            entidade.setLinkBula(rs.getString("LinkBula"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from usuario_paciente where Cpf = ? ";
    }

    @Override
    public void Inserir(Entidade e) throws SQLException, ClassNotFoundException {
        Medicamento medicamento = (Medicamento) e;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = InserirMedicamento();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, medicamento.getNomeComercial());
                stmt.setString(2, medicamento.getNomeGenerico());
                stmt.setString(3, medicamento.getLinkBula());
                stmt.executeUpdate();
            }
        }
    }


    protected String InserirMedicamento() {
        return "insert into " + tabela + " (NomeComercial, NomeGenerico, LinkBula) values (?, ?, ?)";
    }

}