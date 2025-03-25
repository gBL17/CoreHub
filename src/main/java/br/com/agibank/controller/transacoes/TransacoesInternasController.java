package br.com.agibank.controller.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.bo.transacoes.TransacaoBO;
import br.com.agibank.dao.conta.ContaDAO;
import br.com.agibank.dao.AgenciaDAO;
import br.com.agibank.dao.conta.ContaExternaDAO;
import java.sql.SQLException;

public class TransacoesInternasController {
    public static int retornaIdConta(int numeroAgencia, int numeroConta) throws SQLException {
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        ContaDAO contaDAO = new ContaDAO();
        int idAgencia = agenciaDAO.buscarIdAgenciaPorNumero(numeroAgencia);
        int idConta = contaDAO.buscarContaPorIdAgenciaENumeroConta(idAgencia, numeroConta);
        agenciaDAO.fecharConexao();
        return idConta;
    }

    public static int retornaIdContaExterna(int numeroAgencia, int numeroConta) throws SQLException {
        ContaExternaDAO contaExternaDAO = new ContaExternaDAO();
        int id = contaExternaDAO.buscarIdContaExterna(numeroAgencia, numeroConta);
        contaExternaDAO.fecharConexao();
        return id;
    }

    public static void cadastraTransacao(int idContaOrigem, int idContaDestino,int tipoTransacao, int codigoBanco, int agencia, int numeroConta, double valor, String descricao) throws SQLException {
        TransacaoBO transacaoBO = new TransacaoBO();
        Transacao transacao = new Transacao();

        transacao.setIdContaOrigem(idContaOrigem);
        transacao.setIdContaDestino(idContaDestino);
        transacao.setIdTipoTransacao(tipoTransacao);

        if (codigoBanco != 121) {
            transacao.setTransferenciaExterna(true);
            transacao.setIdContaDestino(retornaIdContaExterna(agencia, numeroConta));
        } else {
            transacao.setTransferenciaExterna(false);
            transacao.setIdContaDestino(retornaIdConta(agencia, numeroConta));
        }

        transacao.setValor(valor);
        transacao.setDescricao(descricao);

        transacaoBO.criarTransacao(transacao);
    }
}
