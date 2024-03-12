package com.fiap.ReservasRestaurantes.reserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.service.ReservaService;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {
    private final ReservaService reservaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaController.class);

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> buscarReservas() {
        return ResponseEntity.ok().body(reservaService.buscarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> buscarReserva(@PathVariable UUID id) {
        return ResponseEntity.ok().body(reservaService.buscarReserva(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> inserirReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaSalva = reservaService.inserirReserva(reservaDTO);

        return new ResponseEntity<>(reservaSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirReserva(@PathVariable UUID id) throws ResourceNotFoundException {
        String msg = reservaService.excluirReserva(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/restaurante/{restauranteId}/data/{data}")
    public ResponseEntity<List<Reserva>> listarReservasPorRestauranteEData(@PathVariable UUID restauranteId,
            @PathVariable String data) throws ResourceNotFoundException {

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);
        LocalDate dataReserva = LocalDate.parse(data);

        return ResponseEntity.ok().body(reservaService.encontrarReservasPorRestauranteEData(restaurante, dataReserva));
    }

    @GetMapping("/restaurante/{restauranteId}/cliente/{clienteId}/data/{data}")
    public ResponseEntity<List<Reserva>> listarReservasPorRestauranteEClienteEData(@PathVariable UUID restauranteId, @PathVariable UUID clienteId,
            @PathVariable String data) throws ResourceNotFoundException {

        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        LocalDate dataReserva = LocalDate.parse(data);

        return ResponseEntity.ok().body(reservaService.encontrarReservasPorRestauranteEClienteEData(restaurante, cliente, dataReserva));
    }
}
