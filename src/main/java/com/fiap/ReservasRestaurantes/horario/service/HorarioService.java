package com.fiap.ReservasRestaurantes.horario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.horario.DTO.HorarioDTO;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // add
    @SuppressWarnings("null")
    public HorarioDTO inserirHorario(HorarioDTO horarioDTO) {
        Horario horario = toEntity(horarioDTO);

        // Salva o novo Horario no repositório
        horario = horarioRepository.save(horario);

        // Retorna o novo horario
        return toDTO(horario);
    }

    // read all
    public List<Horario> buscarHorarios() {
        return horarioRepository.findAll();
    }

    // read
    @SuppressWarnings("null")
    public Optional<Horario> buscarHorario(Long id) {
        return horarioRepository.findById(id);
    }

    public HorarioDTO toDTO(Horario horario) {
        return new HorarioDTO(
                horario.getId(),
                horario.getRestaurante(),
                horario.getNome(),
                horario.getAlmocoJantar(),
                horario.getDiaSemana(),
                horario.getInicioHorario(),
                horario.getFimHorario());
    }

    public Horario toEntity(HorarioDTO horarioDTO) {
        // Convertendo HorarioDTO para Horario
        Horario horario = new Horario();
        horario.setId(horarioDTO.id());
        horario.setRestaurante(horarioDTO.restaurante());
        horario.setNome(horarioDTO.nome());
        horario.setAlmocoJantar(horarioDTO.almocoJantar());
        horario.setDiaSemana(horarioDTO.diaSemana());
        horario.setInicioHorario(horarioDTO.inicioHorario());
        horario.setFimHorario(horarioDTO.fimHorario());

        return horario;
    }
}
