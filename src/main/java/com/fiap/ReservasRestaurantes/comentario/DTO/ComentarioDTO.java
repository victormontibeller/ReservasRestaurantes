package com.fiap.ReservasRestaurantes.comentario.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;

public record ComentarioDTO(
    long id,     
    Cliente cliente, 
    String titulo, 
    String texto, 
    int avaliacao,
    LocalDate dataCriacao
) {
    
}
