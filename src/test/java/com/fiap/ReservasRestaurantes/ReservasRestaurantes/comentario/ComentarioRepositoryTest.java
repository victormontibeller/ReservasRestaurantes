package com.fiap.ReservasRestaurantes.ReservasRestaurantes.comentario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.comentario.repository.ComentarioRepository;
import com.fiap.ReservasRestaurantes.comentario.service.ComentarioService;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
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
        Comentario comentario = criarComentarioTeste();

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
        Comentario comentario = criarComentarioTeste();

        when(comentarioRepository.save(comentario)).thenReturn(comentario);
        when(comentarioRepository.findById(comentario.getId())).thenReturn(Optional.of(comentario)); 

        UUID idComentario = comentario.getId();
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
        Comentario comentario = criarComentarioTeste();
        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        comentarioRepository.delete(comentario);

        verify(comentarioRepository, times(1)).delete(comentario);
    }

    /**
     * A method to create a test comment.
     *
     * @return         	the created test comment
     */
    Comentario criarComentarioTeste(){

        Comentario comentario = new Comentario();
        comentario.setId(UUID.randomUUID());
        comentario.setCliente(criaClienteTeste());
        comentario.setTitulo("Restaurante Bom");
        comentario.setTexto("Excelente");
        comentario.setAvaliacao(5);
        comentario.setDataCriacao(LocalDate.now());

        return comentario;
    }

    /**
     * Creates a test client.
     *
     * @return         	the newly created test client
     */
    Cliente criaClienteTeste() {
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setEndereco(new Endereco(UUID.randomUUID(), 
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000"));
        return cliente;
    }

}
