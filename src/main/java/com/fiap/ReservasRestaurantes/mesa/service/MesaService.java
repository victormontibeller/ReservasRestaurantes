package com.fiap.ReservasRestaurantes.mesa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.mesa.DTO.MesaDTO;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.repository.MesaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    // add
    public MesaDTO inserirMesa(MesaDTO MesaDTO) {
        Mesa Mesa = toEntity(MesaDTO);

        // Salva o novo Modelo no repositório
        Mesa = mesaRepository.save(Mesa);

        // Retorna o novo modelo
        return toDTO(Mesa);
    }

    // read all
    public List<Mesa> buscarMesas() {
        return mesaRepository.findAll();
    }

    // read
    public Optional<Mesa> buscarMesa(long id) {
        return mesaRepository.findById(id);
    }

    // delete
    public void excluirMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    public MesaDTO toDTO(Mesa Mesa) {
        return new MesaDTO(
                Mesa.getId(),
                Mesa.getRestaurante(),
                Mesa.getQtdLugares(),
                Mesa.getStatus(),
                Mesa.getPosicao()
        );
    }

    public Mesa toEntity(MesaDTO MesaDTO) {
        // Convertendo MesaDTO para Mesa
        Mesa mesa = new Mesa();
        mesa.setId(MesaDTO.id());
        mesa.setRestaurante(MesaDTO.restaurante());
        mesa.setQtdLugares(MesaDTO.qtdLugares());
        mesa.setStatus(MesaDTO.status());
        mesa.setPosicao(MesaDTO.posicao());

        return mesa;
    }

}
