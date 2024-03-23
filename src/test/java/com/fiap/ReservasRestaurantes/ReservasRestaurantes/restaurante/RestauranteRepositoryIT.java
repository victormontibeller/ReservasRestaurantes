package com.fiap.ReservasRestaurantes.ReservasRestaurantes.restaurante;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.restaurante.DTO.RestauranteDTO;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.repository.RestauranteRepository;
import com.fiap.ReservasRestaurantes.restaurante.service.RestauranteService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class RestauranteRepositoryIT {

    @Autowired
    private RestauranteRepository restauranteRepository;
    
    @Autowired
    private RestauranteService restauranteService;
    
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = restauranteRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    @Test
    void devePermitirInserirNovoReestaurante() {
        assertDoesNotThrow(() -> {
           var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
           restauranteService.inserirRestaurante(restaurante);
        
            assertThat(restaurante).isNotNull().isInstanceOf(RestauranteDTO.class);
            assertThat(restaurante.id()).isNotNull();
            assertThat(restaurante.nome()).isNotNull();
        });
    }   

    @Test
    void devePermitirBuscarRestaurantePorId() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

            Restaurante result = restauranteService.buscarRestaurante(restauranteInsert.id());
        
            assertThat(result).isNotNull();
        });
    }

    @Test
    void devePermitirBuscarTodosOsRestaurantes() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            restauranteService.inserirRestaurante(restaurante);

            var restaurante1 = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste1());
            restauranteService.inserirRestaurante(restaurante1);

            List <Restaurante> result = restauranteService.buscarRestaurantes();

            assertThat(result).isNotNull();
            assertThat(result.size()).isEqualTo(2);

        });
    }

    @Test
    void devePermitirBuscarRestaurantesPorEmail() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

            Restaurante result = restauranteService.buscarRestaurantePorEmail(restauranteInsert.email());
        
            assertThat(result).isNotNull();
        });
    }

    @Test
    void devePermitirBuscarRestaurantePorNome() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

            Restaurante result = restauranteService.buscarRestaurantePorNome(restauranteInsert.nome());
        
            assertThat(result).isNotNull();
        });
    }

    @Test
    void devePermitirDeletarRestaurante() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

            restauranteService.excluirRestaurante(restauranteInsert.id());
            
            assertThrows(ResourceNotFoundException.class, () -> {
                restauranteService.buscarRestaurante(restauranteInsert.id());
            });   

        });

    }
}
