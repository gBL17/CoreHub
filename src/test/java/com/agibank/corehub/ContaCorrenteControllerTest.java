package com.agibank.corehub;

import com.agibank.corehub.controller.conta.ContaCorrenteController;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import static org.junit.Assert.assertTrue;

public class ContaCorrenteControllerTest {

    @Test
    public void testDescontarSaldoContaCorrente() throws SQLException {
        ContaCorrenteController controller = new ContaCorrenteController();
        int resultado = controller.descontarSaldoContaCorrente(1);

        LocalDate hoje = LocalDate.now();
        LocalDate ultimoDia = YearMonth.now().atEndOfMonth();

        if (hoje.equals(ultimoDia)) {
            // Espera-se que retorne um valor maior ou igual a zero (saldo descontado)
            Assert.assertTrue(resultado >= 0);
        } else {
            int diasRestantes = YearMonth.now().lengthOfMonth() - hoje.getDayOfMonth();
            Assert.assertTrue(resultado == diasRestantes);
        }
    }
}

