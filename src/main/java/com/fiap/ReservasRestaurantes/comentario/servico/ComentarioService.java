package com.fiap.ReservasRestaurantes.comentario.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // add
    public ComentarioDTO inserirComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = toEntity(comentarioDTO);

        // Salva o novo Modelo no repositório
        comentario = comentarioRepository.save(comentario);

        // Retorna o novo modelo
        return toDTO(comentario);
    }

    // read all
    public List<Comentario> buscarComentarios() {
        return comentarioRepository.findAll();
    }

    // read
    public Optional<Comentario> buscarComentario(Long id) {
        return comentarioRepository.findById(id);
    }

    public ComentarioDTO toDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getCliente(),
                comentario.getTitulo(),
                comentario.getTexto(),
                comentario.getAvaliacao());
    }

    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        // Convertendo ComentarioDTO para Comentario

        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.id());
        comentario.setCliente(comentarioDTO.cliente());
        comentario.setTitulo(comentarioDTO.titulo());
        comentario.setTexto(comentarioDTO.texto());
        comentario.setAvaliacao(comentarioDTO.avaliacao());

        return comentario;
    }
}
