package com.fiap.ReservasRestaurantes.reserva.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;

public record ReservaDTO(
    long id,
    Cliente cliente,
    Mesa mesa,
    int numPessoas,
    LocalDate dataReserva,
    LocalDate dataCriacao,
    LocalDate inicioReserva,
    int toleranciaMinutos,
    LocalDate horarioLimite
){

}
