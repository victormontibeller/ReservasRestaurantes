package com.fiap.ReservasRestaurantes.endereco.service;

import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

@Service
public class EnderecoService {
    public EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais(),
                endereco.getCep()    
        );

    }

    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        // Convertendo EnderecoDTO para Endereco
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setPais(enderecoDTO.pais());
        endereco.setCep(enderecoDTO.cep());

        return endereco;
    }

}
