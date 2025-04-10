package com.agibank.corehub.dao.conta;

import com.agibank.corehub.beans.classe.Classe;
import com.agibank.corehub.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ClasseDAO() throws SQLException {
        con = Conexao.getConexao();
    }

    public void fecharConexao() throws SQLException {
        con.close();
    }

    public int cadastrarClasse(int score, String tipoClasse) throws SQLException {

        final String sql = "INSERT INTO Classe(score, tipo_classe) VALUES(?,?)";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, score);
        stmt.setString(2, tipoClasse);

        return stmt.executeUpdate();
    }

    public String buscarClasse(int id) throws SQLException {

        final String sql = "SELECT * FROM Classe WHERE id_classe = ?";

        Classe classe = new Classe();

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if(rs.next()){

            classe.setIdClasse(rs.getInt("id_classe"));
            classe.setScore(rs.getInt("score"));
            classe.setTipoClasse(rs.getString("tipo_classe"));

            return classe.toString();

        }else return "Deu errado irmao";

    }

    public int atualizarClasse(int score, String tipoClasse, int id) throws SQLException {

        final String sql = "UPDATE Classe SET score = ?, tipo_classe = ? WHERE id_classe = ?";

        stmt = con.prepareStatement(sql);

        stmt.setInt(1, score);
        stmt.setString(2, tipoClasse);
        stmt.setInt(3, id);

        return stmt.executeUpdate();
    }

    public int deletarClasse(int id) throws SQLException {

        final String sql = "DELETE FROM Classe WHERE id_classe = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);

        return stmt.executeUpdate();

    }
}