package com.agibank.corehub.teste;

import com.agibank.corehub.beans.Usuario;
import com.agibank.corehub.dao.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioTeste {
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void testBuscarUsuarioPorId() throws SQLException {
        int idUsuario = 1; // Defina um ID existente no banco de dados para teste
        Usuario usuarioRetornado = usuarioDAO.buscarUsuarioPorId(idUsuario);
        assertNotNull(usuarioRetornado, "O usuário não deveria ser nulo");
        assertEquals("apelido", usuarioRetornado.getApelido(), "O apelido retornado não corresponde ao esperado");
    }
}
