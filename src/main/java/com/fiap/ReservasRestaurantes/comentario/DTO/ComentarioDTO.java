package com.fiap.ReservasRestaurantes.comentario.DTO;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;

public record ComentarioDTO(
    Long id,     
    Cliente cliente, 
    String titulo, 
    String texto, 
    int avaliacao
) {
    
}
