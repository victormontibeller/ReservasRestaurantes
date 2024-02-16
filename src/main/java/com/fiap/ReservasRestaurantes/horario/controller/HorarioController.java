package com.fiap.ReservasRestaurantes.horario.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.horario.DTO.HorarioDTO;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.service.HorarioService;
import com.fiap.ReservasRestaurantes.reserva.controller.ReservaController;

@RestController
@RequestMapping(value = "/horarios")
public class HorarioController {
    private final HorarioService horarioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaController.class);

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public ResponseEntity<List<Horario>> buscarHorarios() {
        return ResponseEntity.ok().body(horarioService.buscarHorarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Horario>> buscarHorario(@PathVariable long id) {
        return ResponseEntity.ok().body(horarioService.buscarHorario(id));
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> inserirReserva(@RequestBody HorarioDTO horarioDTO) {
        HorarioDTO horarioSalva = horarioService.inserirHorario(horarioDTO);

        return new ResponseEntity<>(horarioSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirHorario(@PathVariable long id) {
        try {
            horarioService.excluirHorario(id);
            LOGGER.info("Horario {} excluido com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível excluir a horario {}!", id);
            return new ResponseEntity<>("Não foi possível excluir o horario!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Reserva excluida com sucesso!", HttpStatus.OK);
    }

}
