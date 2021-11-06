package com.example.datahealthv2.conexao.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.datahealthv2.model.Entidade;
import com.example.datahealthv2.model.Usuario;

public abstract class DAO<E extends Entidade> {

    protected Class<E> entityClass;
    protected final String STRING_CONEXAO = "jdbc:mysql://localhost/datahealth_db?useTimezone=true&serverTimezone=UTC";
    protected final String USUARIO = "root";
    protected final String SENHA = "";
    protected String tabela;

    public DAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected void setTabela(String value) {
        tabela = value;
    }

    public E seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    public E localiza(String codigo) throws SQLException, ClassNotFoundException {
        E entidade = null;
        Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getLocalizaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, codigo);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;
    }

    public abstract void Inserir(Usuario paciente) throws SQLException, ClassNotFoundException;

    protected abstract E preencheEntidade(ResultSet rs);

    public ArrayList<E> lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList();
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            System.out.println("Banco conectado!");
            // ? => binding
            String SQL = getListaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }
        return entidades;
    }

    protected String getListaCommand() {
        return "select * from " + tabela;
    }

    protected abstract String getLocalizaCommand();

    protected E getInstanceOfE() {
        try {
            return entityClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }
}