package com.agibank.corehub.controller.transacao;

import com.agibank.corehub.beans.transacao.StatusTransacao;
import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.controller.conta.ContaController;

import java.sql.SQLException;

public class StatusTransacaoController {
    StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
    ContaController controller = new ContaController();
    TransacaoDAO transacaoDAO = new TransacaoDAO();
    public StatusTransacaoController() throws SQLException {

    }




}
