package com.fiap.ReservasRestaurantes.restaurante.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;

public record RestauranteDTO(
    long id,
    String nome,
    Endereco endereco,
    Horario horario,
    int capacidade,
    StatusRestauranteEnum status,
    LocalDate dataCadastro 
) {
    
}
