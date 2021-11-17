package com.example.datahealthv2.conexao.DAO.usuario;

import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.model.Usuario;
import com.example.datahealthv2.model.UsuarioPaciente;

public class UsuarioDAOFactory {
    public static DAO factory(Usuario user) {
        if (user.getClass().equals(UsuarioPaciente.class)) {
            return new PacienteDAO<>();
        } else {
            return new ProfissionalDAO<>();
        }
    }
}
