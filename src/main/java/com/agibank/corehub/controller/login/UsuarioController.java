package com.agibank.corehub.controller.login;
import com.agibank.corehub.controller.utils.CifradorSenha;
import com.agibank.corehub.dao.UsuarioDAO;
import com.agibank.corehub.beans.Usuario;

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

            System.out.println("Senhas nao compativeis");
            return 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public Usuario buscarPorApelido (String apelido){
        try{
            return usuarioDAO.buscarUsuarioApelido(apelido);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int alterarDadosUsuario(int tabela, String dado, int id_usuario){
        String[] tabelas = {"nome","email","telefone","rua","numero","complemento","apelido"};

        try {
            if(tabela == 3 || tabela == 5){
                return usuarioDAO.atualizarCadastroUsuario(tabelas[tabela-1],Integer.parseInt(dado),id_usuario);
            }else {
                return usuarioDAO.atualizarCadastroUsuario(tabelas[tabela-1],dado,id_usuario);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }


}
