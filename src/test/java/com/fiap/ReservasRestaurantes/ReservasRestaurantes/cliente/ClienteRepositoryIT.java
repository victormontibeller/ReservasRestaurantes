package com.fiap.ReservasRestaurantes.ReservasRestaurantes.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ClienteRepositoryIT {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = clienteRepository.count();
            assertNotNull(totalRegistros, "totalRegistros should not be null"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    @Test 
    void devePermitirInserirNovoCliente() {
        
        fail("Teste ainda não implementado");
    }

    @Test
    void devePermitirBuscarClientePorId() {

        fail("Teste ainda não implementado");
    }

    @Test
    void devePermitirBuscarTodosOsClientes() {

        fail("Teste ainda não implementado");
    }

    @Test
    void devePermitirBuscarClientesPorEmail() {

        fail("Teste ainda não implementado");
    }

    @Test
    void devePermitirBuscarClientePorNome() {

        fail("Teste ainda não implementado");
    }

    @Test
    void devePermitirDeletarCliente() {

        fail("Teste ainda não implementado");
    }


}
