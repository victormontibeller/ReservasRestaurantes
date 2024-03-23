package com.fiap.ReservasRestaurantes.horario.DTO;

import java.time.LocalDate;
import java.util.UUID;

import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

public record HorarioDTO(
                long id,
                Restaurante restaurante,
                String nome,
                TurnoEnum almocoJantar,
                DiaSemanaEnum diaSemana,
                LocalDate inicioHorario,
                LocalDate fimHorario) {
}
