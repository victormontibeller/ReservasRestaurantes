package com.fiap.ReservasRestaurantes.endereco.controller;

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

import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.endereco.service.EnderecoService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarEnderecos() {
        return ResponseEntity.ok().body(enderecoService.buscarEnderecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(enderecoService.buscarEndereco(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> inserirEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoSalva = enderecoService.inserirEndereco(enderecoDTO);

        return new ResponseEntity<>(enderecoSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEndereco(@PathVariable long id) throws ResourceNotFoundException {
        String msg = enderecoService.excluirEndereco(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
