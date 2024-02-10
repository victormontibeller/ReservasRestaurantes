package com.fiap.ReservasRestaurantes.cliente.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

public record ClienteDTO(
    long id,
    String nome,
    LocalDate dataCadastro,
    Endereco endereco
) {
    
}
