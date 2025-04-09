package com.agibank.corehub.controller.transacao.verificacao;

import com.agibank.corehub.beans.transacao.Transacao;
import com.agibank.corehub.controller.Alerta;
import com.agibank.corehub.controller.utils.Navegador;
import com.agibank.corehub.dao.StatusTransacaoDAO;
import com.agibank.corehub.dao.TransacaoDAO;
import com.agibank.corehub.dao.VerificacaoSegurancaDAO;

import java.sql.SQLException;

public class SegurancaTransacaoController {
    private Navegador navegador = new Navegador();

    public boolean verificarSegurancaTransacao(Transacao transacao) throws SQLException {
        if (verificarSeMaiorValorHistoricoTransacaoDaConta(transacao)){
//            navegador.navegarPara();
            return false;
        }

        if (verificarHorarioInseguro(transacao) && verificarSeMaiorQueMediaValor(transacao)){
            //todo chamar tela de verificação de segurança
            return false;
        }

        StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
        statusTransacaoDAO.atualizarStatusTransacao(retornaIdTransacao(transacao), "APROVADA");
        return true;
    }

    public boolean verificarHorarioInseguro(Transacao transacao) throws SQLException {
        VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
//        (verificacaoSegurancaDAO.transacaoCorreuHorarioSeguro(transacao.getId());
        return false;
    }

    public boolean verificarSeMaiorValorHistoricoTransacaoDaConta(Transacao transacao){
        double valorMinimoVerificacaoDeSeguranca = 500.0;
        try{
            VerificacaoSegurancaDAO verificacaoSegurancaDAO = new VerificacaoSegurancaDAO();
            double maiorTransferencia = verificacaoSegurancaDAO.maiorValorTransferenciaAprovada(transacao.getIdContaOrigem());

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
