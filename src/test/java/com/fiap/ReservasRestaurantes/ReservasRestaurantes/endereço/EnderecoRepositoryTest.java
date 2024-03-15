package com.fiap.ReservasRestaurantes.ReservasRestaurantes.endereço;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.endereco.repository.EnderecoRepository;
import com.fiap.ReservasRestaurantes.endereco.service.EnderecoService;

class EnderecoRepositoryTest {
    
    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecosService;

    AutoCloseable openMocks;

    /**
     * A method to set up the test environment before each test.
     *
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * Test for inserting a new address successfully.
     */
    @Test
    void inserirNovoEnderecoComSucesso() {
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        var enderecoSalvo = enderecoRepository.save(endereco);

        assertNotNull(endereco);
        assertThat(endereco).isEqualTo(enderecoSalvo);
    }

    /**
     * buscarEnderecoPorId function for testing purposes.
     * 
     */
    @Test
    void buscarEnderecoPorId() {
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(enderecoRepository.findById(endereco.getId())).thenReturn(Optional.of(endereco));

        long idEndereco = endereco.getId();
        Endereco enderecoSalvo = enderecoRepository.findById(idEndereco).get();

        assertTrue(enderecoRepository.findById(endereco.getId()).isPresent());
        assertEquals(enderecoSalvo, endereco);
        assertThat(enderecoSalvo.getId()).isEqualTo(enderecoSalvo.getId());
         
    }

    /**
     * A description of the entire Java function.
     *
     */
    @Test
    void testeExcluirEnderecoPorId() {
        Endereco endereco = TestHelper.criarEndereco();

        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        enderecoRepository.delete(endereco);

        verify(enderecoRepository, times(1)).delete(endereco);
       
    }


}
