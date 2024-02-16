package com.fiap.ReservasRestaurantes.mesa.DTO;

import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

public record MesaDTO(
    long id,
    Restaurante restaurante,
    int qtdLugares,
    int status
) {
    
}
