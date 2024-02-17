package com.fiap.ReservasRestaurantes.restaurante.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.restaurante.DTO.RestauranteDTO;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestauranteController.class);

    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarRestaurantes() {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurantes());        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurante>> buscarRestaurante(@PathVariable long id) {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurante(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> inserirRestaurante(@RequestBody RestauranteDTO restauranteDTO) {
        RestauranteDTO restauranteSalva = restauranteService.inserirRestaurante(restauranteDTO);

        return new ResponseEntity<>(restauranteSalva, HttpStatus.CREATED);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirRestaurante(@PathVariable long id) {
        try {
            restauranteService.excluirRestaurante(id);
            LOGGER.info("Restaurante {} excluida com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível excluir a restaurante {}!", id);
            return new ResponseEntity<>("Não foi possível excluir a restaurante!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Restaurante excluida com sucesso!", HttpStatus.OK);
    }
    
}
