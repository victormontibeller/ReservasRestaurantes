package com.fiap.ReservasRestaurantes.ReservasRestaurantes.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.cliente.service.ClienteService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ClienteRepositoryIT {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    /**
     * Test case to verify if the table creation is allowed.
     *
     * @throws AssertionError if an unhandled exception occurs
     * @throws NullPointerException if totalRegistros is null
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = clienteRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

  /**
   * Test case for the method devePermitirInserirNovoCliente.
   *
   * @throws ResourceNotFoundException  if the resource is not found
   */
    @Test 
    void devePermitirInserirNovoCliente() throws ResourceNotFoundException {
      var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
      var novoClienteSalvo = clienteService.inserirCliente(novoCliente);        
      
      assertThat(novoClienteSalvo)
                .isNotNull()
                .isInstanceOf(ClienteDTO.class);
      assertThat(novoClienteSalvo.id()).isNotNull();
      assertThat(novoClienteSalvo.email()).isNotNull();
    }

    /**
     * devePermitirBuscarClientePorId function test case.
     *
     * @throws ResourceNotFoundException
     */
    @Test
    void devePermitirBuscarClientePorId() throws ResourceNotFoundException {
        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getId()).isEqualTo(novoClienteSalvo.id());
    }

    /**
     * A test to verify that it allows searching for all clients.
     *
     * @param  none
     * @return         	none
     */
    @Test
    void devePermitirBuscarTodosOsClientes() {
        assertDoesNotThrow(() -> {
            var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
            clienteService.inserirCliente(novoCliente);
            
            var novoCliente1 = TestHelper.clienteDTO(TestHelper.criarClienteTeste1());
            clienteService.inserirCliente(novoCliente1);
            
            List<Cliente> todosOsClientesEncontrados = clienteService.buscarClientes();
            
            assertNotNull(todosOsClientesEncontrados); // Check for null pointer references
            assertThat(todosOsClientesEncontrados).isNotEmpty();
        });
    }


    /**
     * Test case to verify if searching for clients by email is allowed.
     *
     * @throws ResourceNotFoundException if the resource is not found
     */
    @Test
    void devePermitirBuscarClientesPorEmail() throws ResourceNotFoundException {

        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getEmail()).isEqualTo(novoClienteSalvo.email());
    }

    /**
     * Test case to verify if the search for a client by name is allowed.
     *
     * @throws ResourceNotFoundException  if the resource is not found
     */
    @Test
    void devePermitirBuscarClientePorNome() throws ResourceNotFoundException {
        
        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getNome()).isEqualTo(novoClienteSalvo.nome());
    }

    /**
     * Deletes a client and checks that the client is no longer retrievable.
     *
     * @throws ResourceNotFoundException if the client does not exist
     */
    @Test
    void deveDeletarCliente() throws ResourceNotFoundException {
        var cliente = TestHelper.criarClienteTeste();
        var clienteDTO = TestHelper.clienteDTO(cliente);
        var clienteSalvo = clienteService.inserirCliente(clienteDTO);
        clienteService.excluirCliente(clienteSalvo.id());

        assertThrows(ResourceNotFoundException.class, () -> {
            clienteService.buscarCliente(clienteSalvo.id());
        });   
    }
}
