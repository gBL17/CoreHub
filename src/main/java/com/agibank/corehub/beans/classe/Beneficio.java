package com.agibank.corehub.beans.classe;

public class Beneficio {

    private int idBeneficio;
    private String classe;
    private String descricao;

    public Beneficio(int idBeneficio, String classe, String descricao) {
        this.idBeneficio = idBeneficio;
        this.classe = classe;
        this.descricao = descricao;
    }

    public Beneficio(){}

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Beneficio{" +
                "idBeneficio=" + idBeneficio +
                ", classe='" + classe + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
