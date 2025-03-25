package br.com.agibank.dao.conta;

import br.com.agibank.beans.conta.ContaExterna;
import br.com.agibank.beans.conta.ContaSalario;
import br.com.agibank.dao.Conexao;
import com.mysql.cj.xdevapi.JsonValue;

import java.sql.*;

public class ContaExternaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ContaExternaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarContaExterna(int agencia, int numeroContaExterna) throws SQLException {
        final String sql = "INSERT INTO Conta_Externa(agencia, numero_conta_externa) VALUES(?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        return stmt.executeUpdate();
    }

    public ContaExterna buscarContaExterna(int id) throws SQLException {
        final String sql = "SELECT * FROM Conta_Externa WHERE id_conta_externa = ?";

        ContaExterna contaExterna = new ContaExterna();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){
            contaExterna.setIdContaExterna(rs.getInt("id_conta_externa"));
            contaExterna.setAgencia(rs.getInt("agencia"));
            contaExterna.setAgencia(rs.getInt("numero_conta_externa"));
        }

        return contaExterna;
    }

    public int buscarIdContaExterna(int agencia, int numeroContaExterna, int codigoBanco) throws SQLException {
        final String sql = "SELECT id_conta_externa FROM Conta_Externa WHERE agencia = ? AND numero_conta_externa = ? AND codigo_banco = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, codigoBanco);
        rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("id_conta_externa");
        }
        return 0;
    }

    public int atualizarContaExterna(int idContaExterna, int agencia, int numeroContaExterna) throws SQLException {
        final String sql = "UPDATE Conta_Salario SET agencia = ?, numero_conta_externa = ? WHERE id_conta_externa = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, agencia);
        stmt.setInt(2, numeroContaExterna);
        stmt.setInt(3, idContaExterna);
        return stmt.executeUpdate();
    }

    public int deletarContaExterna(int id) throws SQLException {

        final String sql = "DELETE FROM Conta_Externa WHERE id_conta_externa = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }

}
