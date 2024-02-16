package com.fiap.ReservasRestaurantes.reserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.service.ReservaService;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {
    private final ReservaService reservaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    public ReservaController(ReservaService reservaService){
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> buscarReservas() {
        return ResponseEntity.ok().body(reservaService.buscarReservas());        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> buscarReserva(@PathVariable long id) {
        return ResponseEntity.ok().body(reservaService.buscarReserva(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> inserirReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaSalva = reservaService.inserirReserva(reservaDTO);

        return new ResponseEntity<>(reservaSalva, HttpStatus.CREATED);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirReserva(@PathVariable long id) {
        try {
            reservaService.excluirReserva(id);
            LOGGER.info("Reserva {} excluida com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível excluir a reserva {}!", id);
            return new ResponseEntity<>("Não foi possível excluir a reserva!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Reserva excluida com sucesso!", HttpStatus.OK);
    }
    
}
