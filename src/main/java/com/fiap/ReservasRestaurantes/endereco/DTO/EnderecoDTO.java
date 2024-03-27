package com.fiap.ReservasRestaurantes.endereco.DTO;


public record EnderecoDTO(
        long id,
        String rua,
        int numero,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String cep
) {
}


   


