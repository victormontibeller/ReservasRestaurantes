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

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
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

        Reserva reserva = TestHelper.criaReservaTeste();
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

        Reserva reserva = TestHelper.criaReservaTeste();
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
        Reserva reserva = TestHelper.criaReservaTeste();
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
        Reserva reserva = TestHelper.criaReservaTeste();
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        reservaRepository.delete(reserva);

        verify(reservaRepository, times(1)).delete(reserva);
    }



}
