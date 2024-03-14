package com.fiap.ReservasRestaurantes.reserva.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

public record ReservaDTO(
        long id,
        Cliente cliente,
        Restaurante restaurante,
        List<Mesa> mesa,
        int numPessoas,
        LocalDate dataReserva,
        LocalDate dataCriacao,
        LocalDate inicioReserva,
        int toleranciaMinutos,
        LocalDate horarioLimite,
        StatusReservaEnum status) {

}
