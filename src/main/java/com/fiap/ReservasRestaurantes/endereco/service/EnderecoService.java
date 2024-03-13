package com.fiap.ReservasRestaurantes.endereco.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.endereco.repository.EnderecoRepository;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // add
    public EnderecoDTO inserirEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEntity(enderecoDTO);
        // Salva o novo Endereco no repositório
        try {
            endereco = enderecoRepository.save(endereco);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o endereço");
        } catch (ConstraintViolationException ex) {
            new ResourceNotFoundException("Endereço já cadastrado");
        }

        // Retorna o novo endereco
        return toDTO(endereco);
    }

    // read all
    public List<Endereco> buscarEnderecos() {
        return enderecoRepository.findAll();
    }

    // read
    public Endereco buscarEndereco(UUID id) throws ResourceNotFoundException {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado para este id :: " + id));
        return endereco;
    }

    // delete
    public String excluirEndereco(UUID id) throws ResourceNotFoundException {
        try {
            Endereco endereco = enderecoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado para este id :: " + id));
                    
            enderecoRepository.deleteById(endereco.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Endereço não encontrado para este id :: " + id);
        }
        return "Endereço excluído com sucesso!";
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
                endereco.getCep());

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
