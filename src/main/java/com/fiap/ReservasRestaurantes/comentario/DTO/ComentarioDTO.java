package com.fiap.ReservasRestaurantes.comentario.DTO;

import java.time.LocalDate;
import java.util.UUID;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;

public record ComentarioDTO(
    UUID id,     
    Cliente cliente, 
    String titulo, 
    String texto, 
    int avaliacao,
    LocalDate dataCriacao
) {
    
}
