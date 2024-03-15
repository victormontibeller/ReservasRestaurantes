package com.fiap.ReservasRestaurantes.ReservasRestaurantes.mesa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.repository.MesaRepository;
import com.fiap.ReservasRestaurantes.mesa.service.MesaService;

class MesaRepositoryTest {
    
    @Mock
    private MesaRepository mesaRepository;

    @InjectMocks
    private MesaService mesaService;

    AutoCloseable openMocks;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    /**
     * Tear Down the test environment before each test case.
     */
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * A test for inserting a Mesa with success.
     */
    @Test
    void inserirMesaComSucesso() {
        
        Mesa mesa = TestHelper.criarMesaTeste();
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        var result = mesaRepository.save(mesa);

        assertNotNull(mesa);
        assertEquals(mesa, result);
        assertEquals(mesa.getId(), result.getId());
        verify(mesaRepository, times(1)).save(mesa);
    }

    /**
     * A test to buscarMesaPorId
     */
    @Test
    void testeBuscarMesaPorId() {
        Mesa mesa = TestHelper.criarMesaTeste();

        when(mesaRepository.save(mesa)).thenReturn(mesa);
        when(mesaRepository.findById(mesa.getId())).thenReturn(Optional.of(mesa));
        
        var result = mesaRepository.findById(mesa.getId());

        assertTrue(mesaRepository.findById(mesa.getId()).isPresent());
        assertEquals(mesa, result.get());
        assertThat(result.get().getId()).isEqualTo(mesa.getId());
    }

    /**
     * Teste para buscar todas as mesas.
     */
    @Test
    void testeBuscarTodasAsMesas() {
        Mesa mesa = TestHelper.criarMesaTeste();
        when(mesaRepository.save(mesa)).thenReturn(mesa);
        when(mesaRepository.findAll()).thenReturn(List.of(mesa));

        var result = mesaRepository.findAll();

        assertEquals(1, result.size());
        assertNotNull(result);
    }

    /**
     * Teste buscar mesa por numero.
     */
    @Test
    void testeBuscarMesaPorNumero() {
        Mesa mesa = TestHelper.criarMesaTeste();
        when(mesaRepository.save(mesa)).thenReturn(mesa);
        when(mesaRepository.findByNumero(mesa.getNumero())).thenReturn(mesa);

        var result = mesaRepository.findByNumero(mesa.getNumero());

        assertNotNull(result);
        assertEquals(mesa, result);
        assertThat(result.getNumero()).isEqualTo(mesa.getNumero());
    }

    /**
     * Teste de deletar mesa.
     */
    @Test
    void testeDeletarMesa() {
        Mesa mesa = TestHelper.criarMesaTeste();
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        mesaRepository.delete(mesa);

            verify(mesaRepository, times(1)).delete(mesa);
    }
}
