package com.fiap.ReservasRestaurantes.endereco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.endereco.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // add
    @SuppressWarnings("null")
    public EnderecoDTO inserirEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEntity(enderecoDTO);

        // Salva o novo Endereco no repositório
        endereco = enderecoRepository.save(endereco);

        // Retorna o novo endereco
        return toDTO(endereco);
    }

    // read all
    public List<Endereco> buscarEnderecos() {
        return enderecoRepository.findAll();
    }

    // read
    @SuppressWarnings("null")
    public Optional<Endereco> buscarEndereco(Long id) {
        return enderecoRepository.findById(id);
    }

    // delete
    @SuppressWarnings("null")
    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
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

