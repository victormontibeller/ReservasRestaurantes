package com.fiap.ReservasRestaurantes.ReservasRestaurantes.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = clienteRepository.count();
            assertNotNull(totalRegistros, "totalRegistros should not be null"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

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

    @Test
    void devePermitirBuscarClientePorId() throws ResourceNotFoundException {
        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getId()).isEqualTo(novoClienteSalvo.id());
    }

/*    @Test
    void devePermitirBuscarTodosOsClientes() throws ResourceNotFoundException {
        List<Cliente> todosOsClientes = Arrays.asList();
//        todosOsClientes.add(TestHelper.criarClienteTeste());
//        todosOsClientes.add(TestHelper.criarClienteTeste());
        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        
        var novoCliente1 = TestHelper.clienteDTO(TestHelper.criarClienteTeste1());
        var novoClienteSalvo1 = clienteService.inserirCliente(novoCliente1);

        todosOsClientes = clienteService.buscarClientes();

        assertThat(todosOsClientes).isNotEmpty().isNotNull();
        assertThat(todosOsClientes.size()).isEqualTo(2);
    } */

    @Test
    void devePermitirBuscarClientesPorEmail() throws ResourceNotFoundException {

        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getEmail()).isEqualTo(novoClienteSalvo.email());
    }

    @Test
    void devePermitirBuscarClientePorNome() throws ResourceNotFoundException {
        
        var novoCliente = TestHelper.clienteDTO(TestHelper.criarClienteTeste());
    
        var novoClienteSalvo = clienteService.inserirCliente(novoCliente);
        var novoClienteBuscado = clienteService.buscarCliente(novoClienteSalvo.id());
        
        assertThat(novoClienteBuscado).isNotNull();
        assertThat(novoClienteBuscado.getNome()).isEqualTo(novoClienteSalvo.nome());
    }

    /*@Test
    void devePermitirDeletarCliente() throws ResourceNotFoundException {
        var novoCliente = TestHelper.criarClienteTeste();
        var novoClienteDTO = TestHelper.clienteDTO(novoCliente);
    
        var novoClienteSalvo = clienteService.inserirCliente(novoClienteDTO);
        clienteService.excluirCliente(novoCliente.getId());

        assertThat(clienteService.buscarCliente(novoCliente.getId())).isNull();
    }*/


}
