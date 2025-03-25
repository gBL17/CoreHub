package br.com.agibank.dao.taxa;

import br.com.agibank.beans.conta.taxa.Taxa;

import br.com.agibank.dao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TaxaDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarTaxa(int classe, String descricao, double valor) throws SQLException {
        final String sql = "INSERT INTO Taxa (classe, descricao, valor) VALUES (?, ?, ?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, classe);
        stmt.setString(2, descricao);
        stmt.setDouble(3, valor);

        return stmt.executeUpdate();
    }

    public String buscarTaxa(int id) throws SQLException {
        final String sql = "SELECT * FROM Taxa WHERE id_taxa = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            Taxa taxa = new Taxa();
            taxa.setId(rs.getInt("id_taxa"));
            taxa.setClasse(rs.getInt("classe"));
            taxa.setDescricao(rs.getString("descricao"));
            taxa.setValor(rs.getDouble("valor"));

            return taxa.toString();
        } else {
            return "Taxa não encontrada";
        }
    }

    public int atualizarTaxa(Taxa taxa) throws SQLException {
        final String sql = "UPDATE Taxa SET classe = ?, descricao = ?, valor = ? WHERE id_taxa = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, taxa.getClasse());
        stmt.setString(2, taxa.getDescricao());
        stmt.setDouble(3, taxa.getValor());
        stmt.setInt(4, taxa.getId());

        return stmt.executeUpdate();
    }

    public int excluirTaxa(int id) throws SQLException {
        final String sql = "DELETE FROM Taxa WHERE id_taxa = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }
}
