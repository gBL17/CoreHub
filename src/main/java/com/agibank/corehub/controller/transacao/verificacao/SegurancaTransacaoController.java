package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.VerificacaoSegurancaDAO;

import java.sql.SQLException;

public class SegurancaTransacaoController {
    public void verificarSegurancaTransacao(Transacao transacao) throws SQLException {
        if (verificarSeMaiorValorHistoricoTransacaoDaConta(transacao)){
            //todo chamar tela de verificação de segurança
        }

        if (verificarHorarioInseguro(transacao) && verificarSeMaiorQueMediaValor(transacao)){
            //todo chamar tela de verificação de segurança
        }

        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacaoDAO.atualizarStatusTransacao(retornaIdTransacao(transacao), "APROVADA");
    }

    public boolean verificarHorarioInseguro(Transacao transacao) throws SQLException {
        VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
        return !verificacaoSegurancaDAO.transacaoCorreuHorarioSeguro(transacao.getId());
    }

    public boolean verificarSeMaiorValorHistoricoTransacaoDaConta(Transacao transacao){
        double valorMinimoVerificacaoDeSeguranca = 500.0;
        try{
            VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
            double maiorTransferencia = verificacaoSegurancaDAO.maiorValorTransferenciaAprovada(transacao.getIdContaOrigem());
            verificacaoSegurancaDAO.fecharConexao();

            if (maiorTransferencia < transacao.getValor() && maiorTransferencia > valorMinimoVerificacaoDeSeguranca){
                return true;
            }
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro No Banco de dados", e.getMessage());
        }
        return false;
    }

    public boolean verificarSeMaiorQueMediaValor(Transacao transacao) {
        double valorMinimoDeSeguranca = 100.0;
        try {
            VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
            double mediaTransferencia = verificacaoSegurancaDAO.mediaValoresTransacaoMaioresQueMinimo(transacao.getIdContaOrigem(), valorMinimoDeSeguranca);
            verificacaoSegurancaDAO.fecharConexao();
            if(mediaTransferencia < transacao.getValor()) {
                return true;
            }
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro No Banco de dados", e.getMessage());
        }

        return false;
    }

    public int retornaIdTransacao(Transacao transacao) throws SQLException{
        int idTransacao;
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        idTransacao = transacaoDAO.buscarIdTransacao(transacao);
        transacaoDAO.fecharConexao();
        return idTransacao;
    }
}
