package com.example.datahealthv2.login;

import com.example.datahealthv2.model.UsuarioPaciente;
import com.example.datahealthv2.model.UsuarioProfissional;
import junit.framework.Assert;
import org.junit.Test;


import java.sql.SQLException;


public class AcessoTest {

    @Test
    public void testeValidaProfissionalLoginSucesso() throws SQLException, ClassNotFoundException {

        Acesso acesso = new Acesso();
        UsuarioProfissional profissional = new UsuarioProfissional();
        profissional.setCpf("44406367802");
        profissional.setSenha("12345");

       Assert.assertTrue(acesso.validaUsuario(profissional));
    }

    @Test
    public void testeValidaProfissionalLoginErro() throws SQLException, ClassNotFoundException {

        Acesso acesso = new Acesso();
        UsuarioProfissional profissional = new UsuarioProfissional();
        profissional.setCpf("44406367802");
        profissional.setSenha("erro");

        Assert.assertFalse(acesso.validaUsuario(profissional));
    }

    @Test
    public void testeValidaPacienteLoginSucesso() throws SQLException, ClassNotFoundException {

        Acesso acesso = new Acesso();
        UsuarioPaciente paciente = new UsuarioPaciente();
        paciente.setCpf("48608042878");
        paciente.setSenha("12345");

        Assert.assertTrue(acesso.validaUsuario(paciente));
    }

    @Test
    public void testeValidaPacienteLoginErro() throws SQLException, ClassNotFoundException {

        Acesso acesso = new Acesso();
        UsuarioPaciente paciente = new UsuarioPaciente();
        paciente.setCpf("48608042878");
        paciente.setSenha("erro");

        Assert.assertFalse(acesso.validaUsuario(paciente));
    }
}