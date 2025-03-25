package br.com.agibank.controller;

import br.com.agibank.beans.Agencia;
import br.com.agibank.dao.AgenciaDAO;

import java.sql.SQLException;

public class AgenciaController {
    private AgenciaDAO AgenciaDAO = new AgenciaDAO();

    public AgenciaController() throws SQLException {
    }

    public void adicionarAgencia(String rua, int numero, String complemento, String cidade, int numeroAgencia){
        Agencia agencia = new Agencia(rua,numero,complemento,cidade, numeroAgencia);
        try{
            AgenciaDAO.inserirAgencia(rua,numero,complemento,cidade, numeroAgencia);
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
