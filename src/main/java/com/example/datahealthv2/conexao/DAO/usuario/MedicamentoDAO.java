package com.example.datahealthv2.conexao.DAO.usuario;

import java.sql.*;


import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.model.Entidade;
import com.example.datahealthv2.model.Medicamento;
import com.example.datahealthv2.model.Usuario;


public class MedicamentoDAO<E extends Entidade> extends DAO {

    public MedicamentoDAO() {
        super(Medicamento.class);
        setTabela("usuario_paciente");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        Medicamento entidade = new Medicamento();
        try {
            entidade.setId(rs.getInt("Id"));
            entidade.setNomeComercial(rs.getString("NomeComercial"));
            entidade.setNomeGenerico(rs.getString("NomeGenerico"));
            entidade.setLinkBula(rs.getString("LinkBula"));
            // entidade.setRemedios((ArrayList<Medicamento>) rs.getArray("Remedios"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (E) entidade;
    }

    public void Inserir(Medicamento medicamento) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = (Connection) DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getInserirMedicamento();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, medicamento.getNomeComercial());
                stmt.setString(2, medicamento.getNomeGenerico());
                stmt.setString(3, medicamento.getLinkBula());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from usuario_paciente where Cpf = ? ";
    }

    protected String getInserirMedicamento() {
        return "insert into usuario_paciente (NomeComercial, NomeGenerico, LinkBula) values (?, ?, ?)";
    }

    @Override
    public void Inserir(Usuario paciente) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }


}
