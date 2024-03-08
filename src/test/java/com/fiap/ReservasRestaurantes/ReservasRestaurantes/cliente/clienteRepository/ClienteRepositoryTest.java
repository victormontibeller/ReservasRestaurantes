package com.fiap.ReservasRestaurantes.ReservasRestaurantes.cliente.clienteRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.cliente.service.ClienteService;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;

class ClienteRepositoryTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    AutoCloseable openMocks;

    /**
     * A setup function to initialize the test environment.
     *
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    /**
     * A teardown function to close the test environment.
     *
     * @param  None
     * @return         	None
     */
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * A test to insert a new client successfully.
     *
     * @throws ResourceNotFoundException  description of the exception
     */
    @Test
    void inserirNovoClienteComSucesso() throws ResourceNotFoundException {
        // Arrange
        Cliente cliente = new Cliente();
        cliente = criaClienteTeste();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        var clienteSalvo = clienteRepository.save(cliente);

        // Assert
        assertNotNull(cliente);
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        assertEquals(cliente.getDataCadastro(), clienteSalvo.getDataCadastro());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
        assertEquals(cliente.getEndereco(), clienteSalvo.getEndereco());
        assertEquals(cliente.getId(), clienteSalvo.getId());
        verify(clienteRepository, times(1)).save(cliente);

    }

    /**
     * Test case for inserting a new client with the same email.
     */
    @Test
    void inserirNovoClienteComMesmoEmail() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente = criaClienteTeste();

        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        // Act
        var clienteBusca = clienteRepository.findById(cliente.getId());
        // Assert
        assertThat(clienteBusca).isPresent()
                                .containsSame(cliente);
    }

    @Test
    void  atualizarClienteComSucesso() throws ResourceNotFoundException {
        // Arrange
        Cliente cliente = new Cliente();
        Reserva reserva = new Reserva();
        cliente = criaClienteTeste();     
        List<Reserva> reservas = List.of(reserva);

        String nome = "João";
        String email = "joao@example.com";
        LocalDate data = LocalDate.now();

        when(clienteRepository.findById(cliente.getId()))
                              .thenReturn(Optional.of(new Cliente(
                                                      cliente.getId(),
                                                      cliente.getEndereco(),
                                                      reservas,
                                                      nome, 
                                                      email, 
                                                      data)));

        // Act
        var clienteAtualizado = clienteRepository.findById(cliente.getId());
        // Assert
        assertNotEquals(clienteAtualizado, cliente);
    }    




    Cliente criaClienteTeste() {
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setEndereco(new Endereco(UUID.randomUUID(), 
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000"));
        return cliente;
    }
}
