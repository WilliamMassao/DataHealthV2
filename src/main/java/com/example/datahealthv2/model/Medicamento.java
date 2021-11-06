package com.example.datahealthv2.model;

public class Medicamento extends Entidade{
    String NomeComercial;
    String NomeGenerico;
    String LinkBula;

    public Medicamento(String nomeComercial, String nomeGenerico, String linkBula){
        this.NomeComercial = nomeComercial;
        this.NomeGenerico = nomeGenerico;
        this.LinkBula = linkBula;
    }

    public Medicamento() {
    }

    public String getNomeComercial() {
        return this.NomeComercial;
    }

    public void setNomeComercial(String NomeComercial) {
        this.NomeComercial = NomeComercial;
    }

    public String getNomeGenerico() {
        return this.NomeGenerico;
    }

    public void setNomeGenerico(String NomeGenerico) {
        this.NomeGenerico = NomeGenerico;
    }

    public String getLinkBula() {
        return this.LinkBula;
    }

    public void setLinkBula(String LinkBula) {
        this.LinkBula = LinkBula;
    }
}
