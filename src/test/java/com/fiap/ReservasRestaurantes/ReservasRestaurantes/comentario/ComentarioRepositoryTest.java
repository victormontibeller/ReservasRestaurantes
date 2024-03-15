package com.fiap.ReservasRestaurantes.ReservasRestaurantes.comentario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.repository.ComentarioRepository;
import com.fiap.ReservasRestaurantes.comentario.service.ComentarioService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

class ComentarioRepositoryTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    void tearDown() throws Exception {
        openMocks.close(); 
    }
 
    /**
     * A test to insert a new comentario successfully.
     *
     * @throws ResourceNotFoundException  description of the exception
     */
    @Test
    void inserirNovoComentarioComSucesso() throws ResourceNotFoundException {
        Comentario comentario = TestHelper.criarComentarioTeste();

        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        var comentarioSalvo = comentarioRepository.save(comentario);

        assertNotNull(comentarioSalvo);
        assertThat(comentarioSalvo).isEqualTo(comentario);
    }

    /**
     * A test case to search for a comment by its ID.
     */
    @Test
    void testeBuscarComentarioPorId() {
        Comentario comentario = TestHelper.criarComentarioTeste();

        when(comentarioRepository.save(comentario)).thenReturn(comentario);
        when(comentarioRepository.findById(comentario.getId())).thenReturn(Optional.of(comentario)); 

        long idComentario = comentario.getId();
        var comentarioSalvo = comentarioRepository.findById(idComentario);

        assertTrue(comentarioRepository.findById(comentario.getId()).isPresent());
        assertEquals(comentarioSalvo.get(), comentario);
        assertThat(comentarioSalvo.get()).isEqualTo(comentario);

    }

    /**
     * A test for deleting a comment successfully.
     */
    @Test
    void testeDeletarComentarioComSucesso() {
        Comentario comentario = TestHelper.criarComentarioTeste();
        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        comentarioRepository.delete(comentario);

        verify(comentarioRepository, times(1)).delete(comentario);
    }

}
