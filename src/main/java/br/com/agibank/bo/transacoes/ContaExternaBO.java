package br.com.agibank.bo.transacoes;

import br.com.agibank.beans.conta.ContaExterna;
import br.com.agibank.dao.transacoes.ContaExternaDAO;
import java.sql.SQLException;

public class ContaExternaBO {
    private ContaExternaDAO contaExternaDAO;

    public ContaExternaBO() throws SQLException {
        contaExternaDAO = new ContaExternaDAO();
    }

    public int criarContaExterna(int agencia, int numeroConta, int codigoBanco) throws SQLException {
        return contaExternaDAO.criarContaExterna(agencia, numeroConta, codigoBanco);
    }

    public ContaExterna buscarContaExterna(int id) throws SQLException {
        return contaExternaDAO.buscarContaExterna(id);
    }

    public int buscarIdContaExterna(int agencia, int numeroConta, int codigoBanco) throws SQLException {
        return contaExternaDAO.buscarIdContaExterna(agencia,numeroConta, codigoBanco);
    }

    public int atualizarContaExterna(ContaExterna conta) throws SQLException {
        return contaExternaDAO.atualizarContaExterna(conta.getIdContaExterna(), conta.getAgencia(), conta.getNumeroContaExterna(), conta.getCodigoBancoExterno());
    }

    public int excluirContaExterna(int id) throws SQLException {
        return contaExternaDAO.deletarContaExterna(id);
    }
}
