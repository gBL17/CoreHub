package com.agibank.corehub.controller;

import com.agibank.corehub.beans.Agencia;
import com.agibank.corehub.dao.AgenciaDAO;

import java.sql.SQLException;

public class AgenciaController {
    private AgenciaDAO AgenciaDAO = new AgenciaDAO();

    public AgenciaController() throws SQLException {
    }

    public void adicionarAgencia(String rua, int numero, String complemento, String cidade){
        Agencia agencia = new Agencia(rua,numero,complemento,cidade);
        try{
            AgenciaDAO.inserirAgencia(rua,numero,complemento,cidade);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void deletarAgencia(int id_agencia){
        try{
            AgenciaDAO.deletarAgencia(id_agencia);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void alterarEndereco(int id_agencia, String rua, int numero, String complemento, String cidade){
        try{
            AgenciaDAO.alterarEndereco(id_agencia,rua,numero,complemento,cidade);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarAgencias(){
        try{
            AgenciaDAO.listarAgencias();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
