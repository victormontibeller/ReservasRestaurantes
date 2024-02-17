package com.fiap.ReservasRestaurantes.restaurante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.restaurante.DTO.RestauranteDTO;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // add
    public RestauranteDTO inserirRestaurante(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = toEntity(restauranteDTO);

        // Salva o novo Restaurante no repositório
        restaurante = restauranteRepository.save(restaurante);

        // Retorna o novo restaurante
        return toDTO(restaurante);
    }

    // read all
    public List<Restaurante> buscarRestaurantes() {
        return restauranteRepository.findAll();
    }

    // read
    public Optional<Restaurante> buscarRestaurante(Long id) {
        return restauranteRepository.findById(id);
    }

    // delete
    public void excluirRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getHorario(),
                restaurante.getCapacidade(),
                restaurante.getStatus(),
                restaurante.getDataCadastro());
    }

    public Restaurante toEntity(RestauranteDTO restauranteDTO) {
        // Convertendo RestauranteDTO para Restaurante
        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteDTO.id());
        restaurante.setNome(restauranteDTO.nome());
        restaurante.setEndereco(restauranteDTO.endereco());
        restaurante.setHorario(restauranteDTO.horario());
        restaurante.setCapacidade(restauranteDTO.capacidade());
        restaurante.setStatus(restauranteDTO.status());
        restaurante.setDataCadastro(restauranteDTO.dataCadastro());
        
        return restaurante;
    }

    // Métodos do negócio da classe

}
