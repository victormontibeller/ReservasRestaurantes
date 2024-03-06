package com.fiap.ReservasRestaurantes.horario.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.horario.DTO.HorarioDTO;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // add
    public HorarioDTO inserirHorario(HorarioDTO horarioDTO) {
        Horario horario = toEntity(horarioDTO);

        // Salva o novo Horario no repositório
        try {
            horario = horarioRepository.save(horario);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o endereço");
        } catch (ConstraintViolationException ex) {
            new ResourceNotFoundException("Horário já cadastrado");
        }

        // Retorna o novo horario
        return toDTO(horario);
    }

    // read all
    public List<Horario> buscarHorarios() {
        return horarioRepository.findAll();
    }

    // read
    public Horario buscarHorario(Long id) throws ResourceNotFoundException {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario não encontrado para este id :: " + id));
        return horario;
    }

    // delete
    public String excluirHorario(Long id) throws ResourceNotFoundException {
        try {
            Horario horario = horarioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Horario não encontrado para este id :: " + id));

            horarioRepository.deleteById(horario.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Horario não encontrado para este id :: " + id);
        }
        return "Horario excluído com sucesso!";
    }

    public HorarioDTO toDTO(Horario horario) {
        return new HorarioDTO(
                horario.getId(),
                null,
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
       // horario.setRestaurante(horarioDTO.restaurante());
        horario.setNome(horarioDTO.nome());
        horario.setAlmocoJantar(horarioDTO.almocoJantar());
        horario.setDiaSemana(horarioDTO.diaSemana());
        horario.setInicioHorario(horarioDTO.inicioHorario());
        horario.setFimHorario(horarioDTO.fimHorario());

        return horario;
    }
}
