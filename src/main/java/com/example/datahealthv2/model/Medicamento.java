package com.example.datahealthv2.model;

public class Medicamento extends Entidade {
    String NomeComercial;
    String NomeGenerico;
    String LinkBula;
    String Data;

    public Medicamento(String nomeComercial, String nomeGenerico, String linkBula) {
        this.NomeComercial = nomeComercial;
        this.NomeGenerico = nomeGenerico;
        this.LinkBula = linkBula;
    }

    public Medicamento() {

    }

    public String getNomeComercial() {
        return this.NomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.NomeComercial = nomeComercial;
    }

    public String getNomeGenerico() {
        return this.NomeGenerico;
    }

    public void setNomeGenerico(String nomeGenerico) {
        this.NomeGenerico = nomeGenerico;
    }

    public String getLinkBula() {
        return this.LinkBula;
    }

    public void setLinkBula(String linkBula) {
        this.LinkBula = linkBula;
    }

    public String getData() {
        return this.Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

}
