package br.com.agibank.bo.transacoes;

import br.com.agibank.beans.conta.ContaExterna;
import br.com.agibank.dao.transacoes.ContaExternaDAO;
import java.sql.SQLException;

public class ContaExternaBO {
    private ContaExternaDAO contaExternaDAO;

    public ContaExternaBO() throws SQLException {
        contaExternaDAO = new ContaExternaDAO();
    }

    public int criarContaExterna(ContaExterna conta) throws SQLException {
        if (contaExternaDAO.consultarContaExterna(conta.getIdContaExterna()) != null) {
            throw new SQLException("Conta externa já cadastrada, id= " + conta.getIdContaExterna());
        }
        return contaExternaDAO.criarContaExterna(conta.getAgencia(), conta.getNumeroContaExterna());
    }

    public ContaExterna buscarContaExterna(int id) throws SQLException {
        return contaExternaDAO.consultarContaExterna(id);
    }

    public int atualizarContaExterna(ContaExterna conta) throws SQLException {
        return contaExternaDAO.atualizarContaExterna(conta.getIdContaExterna(), conta.getAgencia(), conta.getNumeroContaExterna());
    }

    public int excluirContaExterna(int id) throws SQLException {
        return contaExternaDAO.deletarContaExterna(id);
    }
}
