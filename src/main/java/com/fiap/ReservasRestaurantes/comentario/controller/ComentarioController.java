package com.fiap.ReservasRestaurantes.comentario.controller;

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

import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.service.ComentarioService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.reserva.controller.ReservaController;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaController.class);

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> buscarComentarios() {
        return ResponseEntity.ok().body(comentarioService.buscarComentarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarComentario(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(comentarioService.buscarComentario(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> inserirComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO comentarioSalva = comentarioService.inserirComentario(comentarioDTO);

        return new ResponseEntity<>(comentarioSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirComentario(@PathVariable long id) throws ResourceNotFoundException {
        String msg = comentarioService.excluirComentario(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
