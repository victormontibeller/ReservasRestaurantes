package com.fiap.ReservasRestaurantes.comentario.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.repository.ComentarioRepository;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // add
    public ComentarioDTO inserirComentario(ComentarioDTO comentarioDTO) throws ResourceNotFoundException {
        Comentario comentario = toEntity(comentarioDTO);

        // Salva o novo Comentario no repositório
        try {
            comentario = comentarioRepository.save(comentario);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o comentário");
        } catch (ConstraintViolationException ex) {
            new ResourceNotFoundException("Comentário já cadastrado");
        }

        // Retorna o novo comentario
        return toDTO(comentario);
    }

    // read all
    public List<Comentario> buscarComentarios() throws ResourceNotFoundException {
        List<Comentario> comentario = comentarioRepository.findAll();
        if (comentario.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum comentário encontrado.");
        }
        return comentario;        
    }

    // read
    public Comentario buscarComentario(long id) throws ResourceNotFoundException {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado para este id :: " + id));
        return comentario;
    }

    // delete
    public String excluirComentario(long id) throws ResourceNotFoundException {
        try {
            Comentario comentario = comentarioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado para este id :: " + id));
                    
            comentarioRepository.deleteById(comentario.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Comentário não encontrado para este id :: " + id);
        }
        return "Comentário excluído com sucesso!";
    }

    public ComentarioDTO toDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getCliente(),
                comentario.getTitulo(),
                comentario.getTexto(),
                comentario.getAvaliacao(),
                comentario.getDataCriacao());
    }

    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        // Convertendo ComentarioDTO para Comentario
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.id());
        comentario.setCliente(comentarioDTO.cliente());
        comentario.setTitulo(comentarioDTO.titulo());
        comentario.setTexto(comentarioDTO.texto());
        comentario.setAvaliacao(comentarioDTO.avaliacao());
        comentario.setDataCriacao(comentarioDTO.dataCriacao());

        return comentario;
    }

}
