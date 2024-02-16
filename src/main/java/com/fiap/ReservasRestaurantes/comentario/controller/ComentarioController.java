package com.fiap.ReservasRestaurantes.comentario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.service.ComentarioService;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioController {
    
    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService){
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> buscarComentarios() {
        return ResponseEntity.ok().body(comentarioService.buscarComentarios());        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comentario>> buscarComentario(@PathVariable long id) {
        return ResponseEntity.ok().body(comentarioService.buscarComentario(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> inserirComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO comentarioSalva = comentarioService.inserirComentario(comentarioDTO);

        return new ResponseEntity<>(comentarioSalva, HttpStatus.CREATED);
    } 
}
