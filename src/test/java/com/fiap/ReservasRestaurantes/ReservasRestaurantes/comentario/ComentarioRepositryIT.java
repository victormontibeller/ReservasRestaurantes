package com.fiap.ReservasRestaurantes.ReservasRestaurantes.comentario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.repository.ComentarioRepository;
import com.fiap.ReservasRestaurantes.comentario.service.ComentarioService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ComentarioRepositryIT {
 
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioService comentarioService;

    /**
     * Test case to verify if the table creation is allowed.
     *
     * @throws AssertionError if an unhandled exception occurs during the test.
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = comentarioRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }
        
    @Test
    void devePermitirInserirNovoComentario() throws ResourceNotFoundException {
        var novoComentario = TestHelper.toDTO(TestHelper.criarComentarioTeste());
        
        var novoComentarioSalvo = comentarioService.inserirComentario(novoComentario);

        assertThat(novoComentarioSalvo)
                .isNotNull()
                .isInstanceOf(ComentarioDTO.class);
        assertThat(novoComentarioSalvo.id()).isNotNull();
        assertThat(novoComentarioSalvo.texto()).isNotNull();
    }

    @Test
    void devePermitirBuscarComentarioPorId() throws ResourceNotFoundException {
        var novoComentario = TestHelper.toDTO(TestHelper.criarComentarioTeste());
    
        var novoComentarioSalvo = comentarioService.inserirComentario(novoComentario);
        var novoComentarioBuscado = comentarioService.buscarComentario(novoComentarioSalvo.id());
        
        assertThat(novoComentarioBuscado).isNotNull();
        assertThat(novoComentarioBuscado.getId()).isEqualTo(novoComentarioSalvo.id());
    }
    @Test
    void devePermitirBuscarTodosOsComentarios() throws ResourceNotFoundException {
        var novoComentario = TestHelper.toDTO(TestHelper.criarComentarioTeste());
        comentarioService.inserirComentario(novoComentario);
        var  novoComentario1 = TestHelper.toDTO(TestHelper.criarComentarioTeste1());
        comentarioService.inserirComentario(novoComentario1);

        //List <Comentario> comentarios = comentarioService.buscarComentarios();
        //assertNotNull(comentarios);
        //assertThat(comentarios).isNotEmpty();
    }
    @Test
    void devePermitirDeletarComentario() throws ResourceNotFoundException {
        var  novoComentario = TestHelper.toDTO(TestHelper.criarComentarioTeste());
        var novoComentarioSalvo = comentarioService.inserirComentario(novoComentario);

        assertDoesNotThrow(() -> { // check for null pointer references
            comentarioService.excluirComentario(novoComentarioSalvo.id());
        });
    }
}
