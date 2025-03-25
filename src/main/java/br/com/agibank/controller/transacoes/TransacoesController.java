package br.com.agibank.controller.transacoes;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.bo.transacoes.ContaExternaBO;
import br.com.agibank.bo.transacoes.TransacaoBO;
import br.com.agibank.dao.conta.ContaDAO;
import br.com.agibank.dao.AgenciaDAO;
import br.com.agibank.dao.conta.ContaExternaDAO;
import java.sql.SQLException;

public class TransacoesController {
    public static int retornaIdConta(int numeroAgencia, int numeroConta) throws SQLException {
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        ContaDAO contaDAO = new ContaDAO();
        int idAgencia = agenciaDAO.buscarIdAgenciaPorNumero(numeroAgencia);
        int idConta = contaDAO.buscarContaPorIdAgenciaENumeroConta(idAgencia, numeroConta);
        agenciaDAO.fecharConexao();
        return idConta;
    }

    public static int retornaIdContaExterna(int numeroAgencia, int numeroConta, int codigoConta) throws SQLException {
        ContaExternaBO contaExternaBO = new ContaExternaBO();
        return contaExternaBO.buscarIdContaExterna(numeroAgencia, numeroConta, codigoConta);
    }

    public static void cadastraTransacao(int idContaOrigem, int idContaDestino,int tipoTransacao, int codigoBanco, int agencia, int numeroConta, double valor, String descricao) throws SQLException {
        TransacaoBO transacaoBO = new TransacaoBO();
        ContaExternaBO contaExternaBO = new ContaExternaBO();
        Transacao transacao = new Transacao();

        transacao.setIdContaOrigem(idContaOrigem);
        transacao.setIdContaDestino(idContaDestino);
        transacao.setIdTipoTransacao(tipoTransacao);

        if (codigoBanco != 121) {
            transacao.setTransferenciaExterna(true);
            if (verificaSeContaExternaExiste(agencia, numeroConta, codigoBanco) == 0){
                contaExternaBO.criarContaExterna(agencia, numeroConta, codigoBanco);
                transacao.setIdContaDestino(retornaIdContaExterna(agencia, numeroConta, codigoBanco));
            } else transacao.setIdContaDestino(retornaIdContaExterna(agencia, numeroConta, codigoBanco));
        } else {
            transacao.setTransferenciaExterna(false);
            transacao.setIdContaDestino(retornaIdConta(agencia, numeroConta));
        }

        transacao.setValor(valor);
        transacao.setDescricao(descricao);

        transacaoBO.criarTransacao(transacao);
    }

    private static int verificaSeContaExternaExiste(int numeroAgencia, int numeroConta, int codigoBanco) throws SQLException {
        return retornaIdContaExterna(numeroAgencia,numeroConta,codigoBanco);
    }
}
