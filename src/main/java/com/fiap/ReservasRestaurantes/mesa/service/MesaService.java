package com.fiap.ReservasRestaurantes.mesa.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.mesa.DTO.MesaDTO;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.repository.MesaRepository;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    // add
    public MesaDTO inserirMesa(MesaDTO mesaDTO) throws ResourceNotFoundException {
        Mesa mesa = toEntity(mesaDTO);

        // Salva o novo Horario no repositório
        try {
            mesa = mesaRepository.save(mesa);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar a mesa");
        } catch (ConstraintViolationException ex) {
            new ResourceNotFoundException("Mesa já cadastrado");
        }

        // Retorna o novo modelo
        return toDTO(mesa);
    }

    // read all
    public List<Mesa> buscarMesas() throws ResourceNotFoundException {
        List<Mesa> mesa = mesaRepository.findAll();
        if (mesa.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma mesa encontrado.");
        }
        return mesa;          
    }

    // read
    public Mesa buscarMesa(long id) throws ResourceNotFoundException {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada para este id :: " + id));
        return mesa;
    }

    // read by numero
    public Mesa buscarMesaPorNumero(String numero) throws ResourceNotFoundException {
        Mesa mesa = mesaRepository.findByNumero(numero);
        if (mesa == null) {
            throw new ResourceNotFoundException("Mesa de número " + numero + " não encontrada.");
        }
        return mesa;
    }

    // delete
    public String excluirMesa(long id) throws ResourceNotFoundException {
        try {
            Mesa mesa = mesaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada para este id :: " + id));

            mesaRepository.deleteById(mesa.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Mesa não encontrada para este id :: " + id);
        }
        return "Mesa excluída com sucesso!";
    }

    public MesaDTO toDTO(Mesa Mesa) {
        return new MesaDTO(
                Mesa.getId(),
                Mesa.getNumero(),
                Mesa.getRestaurante(),
                Mesa.getQtdLugares(),
                Mesa.getStatus(),
                Mesa.getPosicao());
    }

    public Mesa toEntity(MesaDTO MesaDTO) {
        // Convertendo MesaDTO para Mesa
        Mesa mesa = new Mesa();
        mesa.setId(MesaDTO.id());
        mesa.setNumero(MesaDTO.numero());
        mesa.setRestaurante(MesaDTO.restaurante());
        mesa.setQtdLugares(MesaDTO.qtdLugares());
        mesa.setStatus(MesaDTO.status());
        mesa.setPosicao(MesaDTO.posicao());

        return mesa;
    }

    public List<Mesa> encontrarMesasReservadasPorRestauranteEData(Restaurante restaurante, LocalDate data)
            throws ResourceNotFoundException {
        List<Mesa> mesas = mesaRepository.findByRestauranteAndReservaDataReserva(restaurante, data);
        if (mesas.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Não há mesas reservadas para o restaurante ID=" + restaurante.getId() + " na data " + data + ".");
        }
        return mesas;
    }

    public List<Mesa> encontrarMesasNaoReservadasPorRestauranteEData(Restaurante restaurante, LocalDate data)
            throws ResourceNotFoundException {
        List<Mesa> mesas = mesaRepository.findByRestauranteAndReservaIsNullAndReservaDataReserva(restaurante, data);
        if (mesas.isEmpty()) {
            throw new ResourceNotFoundException("Não há mesas não reservadas para o restaurante ID="
                    + restaurante.getId() + " na data " + data + ".");
        }
        return mesas;
    }
}
