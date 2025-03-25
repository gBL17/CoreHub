package br.com.agibank.bo.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.dao.transacoes.StatusTransacaoDAO;
import br.com.agibank.dao.transacoes.TransacaoDAO;
import java.sql.SQLException;

public class TransacaoBO {
    private TransacaoDAO transacaoDAO;
    private StatusTransacaoDAO statusTransacaoDAO;

    public TransacaoBO() throws SQLException {
        transacaoDAO = new TransacaoDAO();
        statusTransacaoDAO = new StatusTransacaoDAO();
    }

    public int criarTransacao(Transacao transacao) throws SQLException {
        if(transacao.getValor() <= 0){
            throw new IllegalArgumentException("Valor da transação inválido.");
        }
        if(transacao.getIdContaOrigem() == transacao.getIdContaDestino()){
            throw new IllegalArgumentException("Conta de Origem é igual a Conta de Destino.");
        }

        return transacaoDAO.criarTransacao(transacao);
    }

    public Transacao buscarTransacaoPorId(int id) throws SQLException {
        return transacaoDAO.buscarTransacaoPorId(id);
    }

    public int atualizarTransacao(Transacao transacao) throws SQLException {
        return transacaoDAO.atualizarTransacao(transacao.getId(), transacao.getDescricao());
    }

    public int deletarTransacao(Transacao transacao) throws SQLException {
        return transacaoDAO.deletarTransacao(transacao.getId());
    }
}
