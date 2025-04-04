package com.agibank.corehub.beans.transacao;

import java.time.LocalDateTime;

public class StatusTransacao {
    private int id;
    private int idTransacao;
    private String status;
    private LocalDateTime dataTransacao;

    public StatusTransacao() {
    }

    public StatusTransacao(int idTransacao, String status, LocalDateTime dataTransacao) {
        this.idTransacao = idTransacao;
        this.status = status;
        this.dataTransacao = dataTransacao;
    }

    public StatusTransacao(int id, int idTransacao, String status, LocalDateTime dataTransacao) {
        this.id = id;
        this.idTransacao = idTransacao;
        this.status = status;
        this.dataTransacao = dataTransacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}