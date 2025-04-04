package com.agibank.corehub.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CifradorSenhaTest {

    @Test
    void testarCifradorSenha() {
        CifradorSenha cifradorSenha = new CifradorSenha();
        String senha = "<PASSWORD>";

        String senhaCifrada = cifradorSenha.cifrarSenha(senha);

        assertNotNull(senhaCifrada, "A senha cifrada não pode ser nula");
        assertNotEquals(senha,senhaCifrada, "A senha cifrada não pode ser igual à senha original");
        assertTrue(senhaCifrada.startsWith("$2a$"), "O hash cifrado deve começar com o prefixo padrão do BCrypt");
    }

    @Test
    void testarValidacaoSenhaCifrada() {
        CifradorSenha cifradorSenha = new CifradorSenha();
        String senha = "<PASSWORD>";
        String senhaCifrada = cifradorSenha.cifrarSenha(senha);

        assertTrue(cifradorSenha.validarSenhaCrifrada(senhaCifrada, senha), "A senha deve ser validada corretamente pelo hash.");
    }

    @Test
    void testValidarSenhaCrifrada_RecusaSenhaIncorreta() {
        // Arrange
        CifradorSenha cifradorSenha = new CifradorSenha();
        String senha = "<PASSWORD>";
        String senhaErrada = "<WRONGPASSWORD>";
        String senhaCifrada = cifradorSenha.cifrarSenha(senha);

        // Act & Assert
            assertFalse(cifradorSenha.validarSenhaCrifrada(senhaCifrada, senhaErrada), "A senha incorreta não deve ser validada pelo hash.");
    }

}
