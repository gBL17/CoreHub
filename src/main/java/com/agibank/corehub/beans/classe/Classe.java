package com.agibank.corehub.beans.classe;

public class Classe {

    private int idClasse;
    private int score;
    private String tipoClasse;

    public Classe(int idClasse, int score, String tipoClasse) {
        this.idClasse = idClasse;
        this.score = score;
        this.tipoClasse = tipoClasse;
    }

    public Classe() {}

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTipoClasse() {
        return tipoClasse;
    }

    public void setTipoClasse(String tipoClasse) {
        this.tipoClasse = tipoClasse;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", score=" + score +
                ", tipoClasse='" + tipoClasse + '\'' +
                '}';
    }
}
