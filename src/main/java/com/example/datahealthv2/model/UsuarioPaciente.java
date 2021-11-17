package com.example.datahealthv2.model;

import com.example.datahealthv2.conexao.annotations.CampoNoBanco;

import java.util.ArrayList;

public class UsuarioPaciente extends Usuario {

    @CampoNoBanco(nome = "TipoSanguineo", chave = false)
    String TipoSanguineo;

    @CampoNoBanco(nome = "Email", chave = false)
    String Email;

    @CampoNoBanco(nome = "Telefone", chave = false)
    String Telefone;

    ArrayList<Medicamento> Remedios;

    public UsuarioPaciente() {

    }

    public UsuarioPaciente(String nome, String cpf, String senha, String tipoSanguineo,
                           String email, String telefone) {
        this.Nome = nome;
        this.Cpf = cpf;
        this.Senha = senha;
        this.TipoSanguineo = tipoSanguineo;
        this.Telefone = telefone;
        this.Email = email;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getTelefone() {
        return this.Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    public String getTipoSanguineo() {
        return this.TipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.TipoSanguineo = tipoSanguineo;
    }

    public ArrayList<Medicamento> getRemedios() {
        return this.Remedios;
    }

    public void setRemedios(ArrayList<Medicamento> array) {
        this.Remedios = array;
    }
}