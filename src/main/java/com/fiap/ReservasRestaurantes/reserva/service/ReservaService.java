package com.fiap.ReservasRestaurantes.reserva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // add
    public ReservaDTO inserirReserva(ReservaDTO reservaDTO) {
        Reserva reserva = toEntity(reservaDTO);

        // Salva o novo Reserva no repositório
        reserva = reservaRepository.save(reserva);

        // Retorna o novo reserva
        return toDTO(reserva);
    }

    // read all
    public List<Reserva> buscarReservas() {
        return reservaRepository.findAll();
    }

    // read
    public Optional<Reserva> buscarReserva(Long id) {
        return reservaRepository.findById(id);
    }

    // delete
    public void excluirReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public ReservaDTO toDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getCliente(),
                reserva.getMesa(),
                reserva.getNumPessoas(),
                reserva.getDataReserva(),
                reserva.getDataCriacao(),
                reserva.getInicioReserva(),
                reserva.getToleranciaMinutos(),
                reserva.getHorarioLimite(),
                reserva.getStatus());
    }

    public Reserva toEntity(ReservaDTO reservaDTO) {
        // Convertendo ReservaDTO para Reserva
        Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.id());
        reserva.setCliente(reservaDTO.cliente());
        reserva.setMesa(reservaDTO.mesa());
        reserva.setNumPessoas(reservaDTO.numPessoas());
        reserva.setDataReserva(reservaDTO.dataReserva());
        reserva.setDataCriacao(reservaDTO.dataCriacao());
        reserva.setInicioReserva(reservaDTO.inicioReserva());
        reserva.setToleranciaMinutos(reservaDTO.toleranciaMinutos());
        reserva.setHorarioLimite(reservaDTO.horarioLimite());

        return reserva;
    }

    // Métodos do negócio da classe
    public Optional<List<ReservaDTO>> buscarReservaPorRestaurante(long restauranteId) {
        return null;
    }

    public Optional<List<ReservaDTO>> buscarReservaPorCliente(long restauranteId, long clienteId) {
        return null;
    }

    public void cancelarTodasReservasPorRestaurante(long restauranteId) {

    }

    public void cancelarTodasReservaPorRestaurante(long restauranteId, long clienteId) {

    }

    public Optional<List<ReservaDTO>> excluirReservasExpiradas(long restauranteId) {
        return null;
    }

}
