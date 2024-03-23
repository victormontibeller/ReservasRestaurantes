package com.fiap.ReservasRestaurantes.ReservasRestaurantes.restaurante;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.repository.RestauranteRepository;
import com.fiap.ReservasRestaurantes.restaurante.service.RestauranteService;

class RestauranteRepositoryTest {
    
    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteService Service;

    AutoCloseable openMocks;

    /**
     * A setup function to initialize the test environment.
     *
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    /**
     * A teardown function to close the test environment.
     *
     */
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void inserirNovoRestauranteComSucesso() {
        
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);

        var result = restauranteRepository.save(restaurante);

        assertNotNull(result);
        assertEquals(restaurante, result);
        assertEquals(restaurante.getId(), result.getId());
        verify(restauranteRepository, times(1)).save(restaurante);
    }

    @Test
    void testeBuscarRestaurantePorId() {
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);
        when(restauranteRepository.findById(restaurante.getId())).thenReturn(java.util.Optional.of(restaurante));
    
        var result = restauranteRepository.findById(restaurante.getId());

        assertTrue(restauranteRepository.findById(restaurante.getId()).isPresent());
        assertEquals(restaurante, result.get());
    }

    @Test
    void testeBuscarTodosRestaurantes() {
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);
        when(restauranteRepository.findAll()).thenReturn(List.of(restaurante));

        var result = restauranteRepository.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testeBuscarRestaurantePorNome() {
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);
        when(restauranteRepository.findByNome(restaurante.getNome())).thenReturn(restaurante);

        var result = restauranteRepository.findByNome(restaurante.getNome());

        assertNotNull(result);
        assertEquals(restaurante, result);
        assertEquals(restaurante.getNome(), result.getNome());
    }

    @Test
    void testeBuscarRestaurantePorEmail() {
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);
        when(restauranteRepository.findByEmail(restaurante.getEmail())).thenReturn(restaurante);

        var result = restauranteRepository.findByEmail(restaurante.getEmail());

        assertNotNull(result);
        assertEquals(restaurante, result);
        assertEquals(restaurante.getEmail(), result.getEmail());
    }


    @Test
    void testeDeletarRestaurante() {
        Restaurante restaurante = TestHelper.criarRestauranteTeste();
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);

        restauranteRepository.delete(restaurante);

        verify(restauranteRepository, times(1)).delete(restaurante);
    }



}
