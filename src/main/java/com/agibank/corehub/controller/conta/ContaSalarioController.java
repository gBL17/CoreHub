package com.agibank.corehub.controller.conta;

import com.agibank.corehub.dao.conta.ContaSalarioDAO;

import java.sql.SQLException;

public class ContaSalarioController {

    private final ContaSalarioDAO contaSalarioDAO = new ContaSalarioDAO();


    public ContaSalarioController() throws SQLException {
    }

    public int compararCnpjContaSalarioDocumento(int idConta) throws SQLException{

        if(contaSalarioDAO.pegarCnpjContaSalario(idConta) == Integer.parseInt(contaSalarioDAO.pegarCnpjDocumento(idConta))){
            return 1;
        }else {return 0;}

    }

}
