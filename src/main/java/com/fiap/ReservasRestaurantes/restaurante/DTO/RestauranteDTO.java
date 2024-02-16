package com.fiap.ReservasRestaurantes.restaurante.DTO;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;

public record RestauranteDTO(
    long id,
    String nome,
    Endereco endereco,
    Horario horario,
    int capacidade
) {
    
}
