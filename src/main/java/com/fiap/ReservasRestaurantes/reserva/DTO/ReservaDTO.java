package com.fiap.ReservasRestaurantes.reserva.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;

public record ReservaDTO(
    long id,
    Cliente cliente,
    Mesa mesa,
    int numPessoas,
    LocalDate dataReserva,
    LocalDate dataCriacao,
    LocalDate inicioReserva,
    int toleranciaMinutos,
    LocalDate horarioLimite,
    StatusReservaEnum status
){

}
