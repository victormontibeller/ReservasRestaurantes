package com.fiap.ReservasRestaurantes.restaurante.controller;

import java.util.List;
import java.util.UUID;

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

import com.fiap.ReservasRestaurantes.restaurante.DTO.RestauranteDTO;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;
import com.fiap.ReservasRestaurantes.restaurante.service.RestauranteService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.restaurante.controller.RestauranteController;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestauranteController.class);

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarRestaurantes() {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurante(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Restaurante> buscarRestaurantePorEmail(@PathVariable String email)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurantePorEmail(email));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Restaurante> buscarRestaurantePorNome(@PathVariable String nome)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurantePorNome(nome));
    }

    @GetMapping("/parteDoNome/{parteDoNome}")
    public ResponseEntity<List<Restaurante>> buscarRestaurantePorParteDoNome(@PathVariable String parteDoNome)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.buscarRestaurantePorParteDoNome(parteDoNome));
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> inserirRestaurante(@RequestBody RestauranteDTO restauranteDTO)
            throws ResourceNotFoundException {
        RestauranteDTO restauranteSalva = restauranteService.inserirRestaurante(restauranteDTO);

        return new ResponseEntity<>(restauranteSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirRestaurante(@PathVariable UUID id) throws ResourceNotFoundException {
        String msg = restauranteService.excluirRestaurante(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Restaurante>> buscarPorCidade(@PathVariable String cidade)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.encontrarPorCidade(cidade));
    }

    @GetMapping("/cidade-bairro/{cidade}/{bairro}")
    public ResponseEntity<List<Restaurante>> buscarPorCidadeBairro(@PathVariable String cidade,
            @PathVariable String bairro) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.encontrarPorCidadeEBairro(cidade, bairro));
    }

    @GetMapping("/tipo-cozinha/{tipoCozinha}")
    public ResponseEntity<List<Restaurante>> buscarPorTipoCozinha(@PathVariable TipoCozinhaEnum tipoCozinha)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(restauranteService.encontrarPorTipoCozinha(tipoCozinha));
    }
}
