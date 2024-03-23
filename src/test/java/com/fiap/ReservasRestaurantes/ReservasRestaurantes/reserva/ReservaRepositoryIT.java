package com.fiap.ReservasRestaurantes.ReservasRestaurantes.reserva;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.repository.ReservaRepository;
import com.fiap.ReservasRestaurantes.reserva.service.ReservaService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class ReservaRepositoryIT {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;
    
    /**
     * Test case to verify if the table creation is allowed.
     *
     * @throws AssertionError if an unhandled exception occurs
     * @throws NullPointerException if totalRegistros is null
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = reservaRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }    

    /**
     * Test case to verify if a new reservation can be inserted.
     *
     * @Test
     * @return void
     */
    @Test
    void devePermitirInserirNovaReserva() {
        assertDoesNotThrow(() -> {
            var novaReserva = TestHelper.reservaDTO(TestHelper.criarReservaTeste());

            var novaReservaSalva = reservaService.inserirReserva(novaReserva);

            assertThat(novaReservaSalva)
                       .isNotNull()
                       .isInstanceOf(ReservaDTO.class);

            assertThat(novaReservaSalva).isNotNull();
            assertThat(novaReservaSalva.id()).isNotNull();
            assertThat(novaReservaSalva.dataCriacao()).isNotNull();
        });
        
    }

    /**
     * Test case to verify if a reservation can be searched by its ID.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void devePermitirBuscarReservaPorId() {
        assertDoesNotThrow(() -> {
            var novaReserva = TestHelper.reservaDTO(TestHelper.criarReservaTeste());
            reservaService.inserirReserva(novaReserva);

            var reservaBuscada = reservaService.buscarReserva(novaReserva.id());

            assertThat(reservaBuscada).isNotNull();            
        });
    }

    @Test
    void devePermitirBuscarTodasAsReservas() {
        assertDoesNotThrow(() -> {
            var  novaReserva = TestHelper.reservaDTO(TestHelper.criarReservaTeste());
            reservaService.inserirReserva(novaReserva);

            List<Reserva> reservas = reservaService.buscarReservas();

            assertThat(reservas).isNotNull();
            assertThat(reservas).isNotEmpty();
        });
    }

    @Test
    void devePermitirDeletarReserva() throws ResourceNotFoundException {
        var novaReserva = TestHelper.reservaDTO(TestHelper.criarReservaTeste());
        reservaService.inserirReserva(novaReserva);
        assertThat(novaReserva).isNotNull();

        reservaService.excluirReserva(novaReserva.id());
        
        assertThat(reservaService.buscarReserva(novaReserva.id())).isEmpty(); 

    }

}
