package com.fiap.ReservasRestaurantes.ReservasRestaurantes.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;
import com.fiap.ReservasRestaurantes.reserva.repository.ReservaRepository;
import com.fiap.ReservasRestaurantes.reserva.service.ReservaService;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

class ReservaRepositoryTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    AutoCloseable openMocks;

    /**
     * Set up the test environment before each test.
     *
     * @param  None
     * @return         	None
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    /**
     * A method that is executed after each test to clean up resources.
     *
     * @throws Exception    description of the exception that could be thrown
     */
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * A test for inserting a new reservation successfully.
     */
    @Test
    void inserirNovaReservaComSucesso() {

        Reserva reserva = criaReservaTeste();
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        var novaReserva = reservaRepository.save(reserva);

        assertNotNull(novaReserva);
        assertEquals(reserva.getId(), novaReserva.getId());
        verify(reservaRepository, times(1)).save(reserva);
    }

    /**
     * A test for searching for a reservation successfully.
     */
    @Test
    void buscarReservaPorId() {

        Reserva reserva = criaReservaTeste();
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        when(reservaRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        

        assertTrue(reservaRepository.findById(reserva.getId()).isPresent());
        assertEquals(reserva, reservaRepository.findById(reserva.getId()).get());
    }

    /**
     * A test for seraching for all reservation successfully.
     */
    @Test
    void testeBuscarTodasAsReservas() {
        Reserva reserva = criaReservaTeste();
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        when(reservaRepository.findAll()).thenReturn(List.of(reserva));

        List<Reserva> result = reservaRepository.findAll();

        assertEquals(1, result.size());
        assertNotNull(result);
    }
    
    /**
     * A method to remove a reservation by its ID.
     */
    @Test
    void removerReservaPorId() {
        Reserva reserva = criaReservaTeste();
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        reservaRepository.delete(reserva);

        verify(reservaRepository, times(1)).delete(reserva);
    }

    /**
     * Creates a test reservation.
     *
     * @return         the test reservation
     */
    Reserva criaReservaTeste() {
        List<Mesa> mesas = List.of();
        return new Reserva(UUID.randomUUID(),
                    criarClienteTeste(),
                    criarRestauranteTeste(),
                    mesas,
                    2,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now(),
                    10,
                    LocalDate.now(),
                    StatusReservaEnum.ATIVO);
    }
    
    /**
     * Creates and returns a test client with dummy data.
     *
     * @return         	the created test client
     */
    Cliente criarClienteTeste() {
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

    /**
     * Create a test restaurant with empty schedules, reservations, and tables, and a randomly generated address. 
     *
     * @return         	the created restaurant
     */
    Restaurante criarRestauranteTeste() {
        List<Horario> horarios = List.of();
        List<Reserva> reservas = List.of();
        List<Mesa> mesas = List.of();
        Endereco endereco = new Endereco(UUID.randomUUID(), 
                                     "rua abc",     
                                  123,
                                  "Centro", 
                                  "São Paulo",
                                  "SP", 
                                    "Brasil", 
                                     "00000-000");
                                     
        Restaurante restaurante = new Restaurante(UUID.randomUUID(),
                                                "Dois Irmaos",
                                                endereco,
                                                "doisIrmaos@doisIrmaos.com",
                                                horarios,
                                                reservas,
                                                mesas,
                                                TipoCozinhaEnum.INTERNACIONAL,
                                                10,
                                                StatusRestauranteEnum.ATIVO,
                                                LocalDate.now()); 
                                                    
        return restaurante;
    }

}
