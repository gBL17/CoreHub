package br.com.agibank.controller;
import br.com.agibank.dao.UsuarioDAO;
import br.com.agibank.beans.Usuario;
import br.com.agibank.view.CoresTerminal;

import java.sql.SQLException;
import java.time.LocalDate;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioController() throws SQLException {
    }

    public Usuario buscarDadosUsuario(int id_usuario) throws SQLException{
        return usuarioDAO.buscarUsuarioPorId(id_usuario);
    }

    public boolean verificarSenha(String senha, String confirmarSenha){
        return senha.equals(confirmarSenha);
    }


    public int cadastrarUsuario(String nome, String senha, String confirmacaoSenha, int telefone, String apelido, String email, String rua, int numero, String complemento) throws SQLException{
        CifradorSenha cifradorSenha = new CifradorSenha();

        try{
            if(verificarSenha(senha,confirmacaoSenha)){
                Usuario usuario = new Usuario(1, nome, apelido, senha, email,telefone,  LocalDate.of(2020, 1, 8), rua, numero,complemento);
                return usuarioDAO.criarUsuario(usuario);
            }

            System.out.println(CoresTerminal.getRED() + "Senhas nao compativeis" + CoresTerminal.getRESET());
            return 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

}
