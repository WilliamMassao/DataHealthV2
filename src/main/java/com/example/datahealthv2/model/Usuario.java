package com.example.datahealthv2.model;

import com.example.datahealthv2.conexao.annotations.CampoNoBanco;

public abstract class Usuario extends Entidade {

    @CampoNoBanco(nome = "Nome", chave = false)
    public String Nome;

    @CampoNoBanco(nome = "Cpf", chave = false)
    public String Cpf;

    @CampoNoBanco(nome = "Senha", chave = false)
    public String Senha;


    public String getCpf() {
        return this.Cpf;
    }

    public void setCpf(String cpf) {
        this.Cpf = cpf;
    }

    public String getSenha() {
        return this.Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }

    public String getNome() {
        return this.Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }
}
