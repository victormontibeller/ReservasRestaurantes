package com.fiap.ReservasRestaurantes.restaurante.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

public record RestauranteDTO(
        long id,
        String nome,
        Endereco endereco,
        String email,
        List<Horario> horario,
        List<Reserva> reserva,
        List<Mesa> mesa,
        TipoCozinhaEnum tipoCozinha,
        int capacidade,
        StatusRestauranteEnum status,
        LocalDate dataCadastro) {
}
