package com.example.datahealthv2.model;

import com.example.datahealthv2.conexao.annotations.CampoNoBanco;

public class UsuarioProfissional extends Usuario{

    @CampoNoBanco(nome="Matricula", chave = false)
    String Matricula;

    @CampoNoBanco(nome="Cargo", chave = false)
    String Cargo;

    @CampoNoBanco(nome="RegistroProfissional", chave = false)
    String RegistroProfissional;

    public UsuarioProfissional(String nome, String cpf, String senha,
                               String matricula, String cargo, String registroProfissional )
    {
        this.Nome = nome;
        this.Senha = senha;
        this.Cpf = cpf;
        this.Cargo = cargo;
        this.Matricula = matricula;
        this.RegistroProfissional = registroProfissional;
    }

    public UsuarioProfissional() {
    }

    public enum Cargo {
        MEDICO, ENFERMEIRO
    }

    public String getMatricula() {
        return this.Matricula;
    }

    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public String getCargo() {
        return this.Cargo;
    }

    public void setCargo(String cargo ) {
        this.Cargo = cargo;
    }

    public String getRegistroProfissional() {
        return this.RegistroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.RegistroProfissional = registroProfissional;
    }

}
