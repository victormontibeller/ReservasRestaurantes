package com.fiap.ReservasRestaurantes.mesa.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.fiap.ReservasRestaurantes.mesa.DTO.MesaDTO;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.service.MesaService;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.mesa.controller.MesaController;

@RestController
@RequestMapping(value = "/mesas")
public class MesaController {

    private final MesaService mesaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MesaController.class);

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> buscarMesas() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(mesaService.buscarMesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarMesa(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(mesaService.buscarMesa(id));
    }
    /*
     * @GetMapping("/{email}/email")
     * public ResponseEntity<Mesa> buscarMesaPorEmail(@PathVariable String email)
     * throws ResourceNotFoundException {
     * return ResponseEntity.ok().body(mesaService.buscarMesaPorEmail(email));
     * }
     * 
     * @GetMapping("/{nome}/nome")
     * public ResponseEntity<Mesa> buscarMesaPorNome(@PathVariable String nome)
     * throws ResourceNotFoundException {
     * return ResponseEntity.ok().body(mesaService.buscarMesaPorNome(nome));
     * }
     * 
     * @GetMapping("/{parteDoNome}/parteDoNome")
     * public ResponseEntity<List<Mesa>> buscarMesaPorParteDoNome(@PathVariable
     * String parteDoNome)
     * throws ResourceNotFoundException {
     * return
     * ResponseEntity.ok().body(mesaService.buscarMesaPorParteDoNome(parteDoNome));
     * }
     */

    @PostMapping
    public ResponseEntity<MesaDTO> inserirMesa(@RequestBody MesaDTO mesaDTO)
            throws ResourceNotFoundException {
        MesaDTO mesaSalva = mesaService.inserirMesa(mesaDTO);

        return new ResponseEntity<>(mesaSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirMesa(@PathVariable long id) throws ResourceNotFoundException {
        String msg = mesaService.excluirMesa(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/restaurante/{restauranteId}/data/{data}")
    public ResponseEntity<List<Mesa>> listarMesasReservadasPorRestauranteEData(@PathVariable long restauranteId,
            @PathVariable String data) throws ResourceNotFoundException {

        LocalDate dataReserva = LocalDate.parse(data);

        Restaurante restaurante = new Restaurante(); // Obtenha o restaurante com base no restauranteId
        restaurante.setId(restauranteId);

        return ResponseEntity.ok()
                .body(mesaService.encontrarMesasReservadasPorRestauranteEData(restaurante, dataReserva));

    }

    @GetMapping("/restaurante/{restauranteId}/nao-reservadas/data/{data}")
    public ResponseEntity<List<Mesa>> listarMesasNaoReservadasPorRestauranteEData(@PathVariable long restauranteId,
            @PathVariable String data) throws ResourceNotFoundException {

        LocalDate dataReserva = LocalDate.parse(data);
        
        Restaurante restaurante = new Restaurante(); // Obtenha o restaurante com base no restauranteId
        restaurante.setId(restauranteId);

        return ResponseEntity.ok()
                .body(mesaService.encontrarMesasNaoReservadasPorRestauranteEData(restaurante, dataReserva));
    }
}
