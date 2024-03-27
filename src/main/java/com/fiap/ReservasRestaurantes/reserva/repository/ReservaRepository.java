package com.fiap.ReservasRestaurantes.reserva.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByRestauranteAndDataReserva(Restaurante restaurante, LocalDate dataReserva);

    List<Reserva> findByClienteAndDataReserva(Cliente cliente, LocalDate dataReserva);

    List<Reserva> findByRestauranteAndClienteAndDataReserva(Restaurante restaurante, Cliente cliente, LocalDate dataReserva);
}
