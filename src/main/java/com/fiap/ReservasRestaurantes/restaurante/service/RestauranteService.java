package com.fiap.ReservasRestaurantes.restaurante.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.restaurante.DTO.RestauranteDTO;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;
import com.fiap.ReservasRestaurantes.restaurante.repository.RestauranteRepository;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // add
    public RestauranteDTO inserirRestaurante(RestauranteDTO restauranteDTO) throws ResourceNotFoundException {
        Restaurante restaurante = toEntity(restauranteDTO);

        // Salva o novo Modelo no repositório
        restaurante = restauranteRepository.save(restaurante);

        try {
            restaurante = restauranteRepository.save(restaurante);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o restaurante");
        }

        // Retorna o novo modelo
        return toDTO(restaurante);
    }

    // read all
    public List<Restaurante> buscarRestaurantes() {
        return restauranteRepository.findAll();
    }

    // read
    public Restaurante buscarRestaurante(long id) throws ResourceNotFoundException {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado para este id :: " + id));
        return restaurante;
    }

    // read by email
    public Restaurante buscarRestaurantePorEmail(String email) throws ResourceNotFoundException {
        Restaurante restaurante = restauranteRepository.findByEmail(email);
        if (restaurante == null) {
            throw new ResourceNotFoundException("Restaurante com o email " + email + " não encontrado.");
        }
        return restaurante;
    }

    // read by nome
    public Restaurante buscarRestaurantePorNome(String nome) throws ResourceNotFoundException {
        Restaurante restaurante = restauranteRepository.findByNome(nome);
        if (restaurante == null) {
            throw new ResourceNotFoundException("Restaurante com o nome " + nome + " não encontrado.");
        }
        return restaurante;
    }

    // read by parte do nome
    public List<Restaurante> buscarRestaurantePorParteDoNome(String parteDoNome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(parteDoNome);
    }

    // delete
    public String excluirRestaurante(long id) throws ResourceNotFoundException {
        try {
            Restaurante restaurante = restauranteRepository.findById(id)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Restaurante não encontrado para este id :: " + id));

            restauranteRepository.deleteById(restaurante.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Restaurante não encontrado para este id :: " + id);
        }
        return "Restaurante excluído com sucesso!";
    }

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getEmail(),
                restaurante.getHorario(),
                restaurante.getReserva(),
                restaurante.getMesa(),
                restaurante.getTipoCozinha(),
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
        restaurante.setEmail(restauranteDTO.email());
        restaurante.setHorario(restauranteDTO.horario());
        restaurante.setReserva(restauranteDTO.reserva());
        restaurante.setMesa(restauranteDTO.mesa());
        restaurante.setTipoCozinha(restauranteDTO.tipoCozinha());
        restaurante.setCapacidade(restauranteDTO.capacidade());
        restaurante.setStatus(restauranteDTO.status());
        restaurante.setDataCadastro(restauranteDTO.dataCadastro());

        return restaurante;
    }

    public List<Restaurante> encontrarPorCidade(String cidade) throws ResourceNotFoundException {
        List<Restaurante> restaurantes = restauranteRepository.findByCidade(cidade);
        if (restaurantes.isEmpty()) {
            throw new ResourceNotFoundException("Não há restaurantes cadastrados para a cidade " + cidade + ".");
        }
        return restaurantes;
    }

    public List<Restaurante> encontrarPorCidadeEBairro(String cidade, String bairro) throws ResourceNotFoundException {
        List<Restaurante> restaurantes = restauranteRepository.findByCidadeAndBairro(cidade, bairro);
        if (restaurantes.isEmpty()) {
            throw new ResourceNotFoundException("Não há restaurantes cadastrados para a cidade " + cidade + " e bairro " + bairro + ".");
        }
        return restaurantes;      
    }

    public List<Restaurante> encontrarPorTipoCozinha(TipoCozinhaEnum tipoCozinha) throws ResourceNotFoundException {
        List<Restaurante> restaurantes = restauranteRepository.findByTipoCozinha(tipoCozinha);
        if (restaurantes.isEmpty()) {
            throw new ResourceNotFoundException("Não há restaurantes cadastrados para o tipo de cozinha " + tipoCozinha + ".");
        }
        return restaurantes; 
    }

}
