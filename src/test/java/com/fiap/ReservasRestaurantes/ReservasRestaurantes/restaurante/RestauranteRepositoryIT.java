package com.fiap.ReservasRestaurantes.ReservasRestaurantes.restaurante;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
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

    /**
     * Test case to verify if the table creation is allowed.
     *
     * @throws AssertionError if an unhandled exception occurs
     * @throws NullPointerException if totalRegistros is null
     */    
    
    // @Test
    // void devePermitirCriarTabela() {
    //     assertDoesNotThrow(() -> { // Check for unhandled exceptions
    //         var totalRegistros = restauranteRepository.count();
    //         assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
    //         assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
    //     });
    // }

/**
   * Test case for the method devePermitirInserirNovoCliente.
   *
   * @throws ResourceNotFoundException  if the resource is not found
   */

    // @Test
    // void devePermitirInserirNovoReestaurante() throws ResourceNotFoundException {
    //        var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
           
    //        var restauranteSalvo = restauranteService.inserirRestaurante(restaurante);
        
    //         assertThat(restauranteSalvo).isNotNull().isInstanceOf(RestauranteDTO.class);
    //         assertThat(restauranteSalvo.id()).isNotNull();
    //         assertThat(restauranteSalvo.nome()).isNotNull();
    //     });
    // }   

    /**
     * devePermitirBuscarClientePorId function test case.
     *
     * @throws ResourceNotFoundException
     */    
    // @Test
    // void devePermitirBuscarRestaurantePorId() throws ResourceNotFoundException {
    //         var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            
    //         var restauranteInsert = restauranteService.inserirRestaurante(restaurante);
    //         var result = restauranteService.buscarRestaurante(restauranteInsert.id());
        
    //         assertThat(result).isNotNull();
    //     });
    // }

    @Test
    void devePermitirBuscarTodosOsRestaurantes() {
        assertDoesNotThrow(() -> {
            var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
            restauranteService.inserirRestaurante(restaurante);

            var restaurante1 = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste1());
            restauranteService.inserirRestaurante(restaurante1);

            // List<Restaurante> result = restauranteService.buscarRestaurantes();

            // assertThat(result).isNotNull();
            // assertThat(result.size()).isEqualTo(2);

        });
    }

    // @Test
    // void devePermitirBuscarRestaurantesPorEmail() {
    //     assertDoesNotThrow(() -> {
    //         var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
    //         var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

    //         Restaurante result = restauranteService.buscarRestaurantePorEmail(restauranteInsert.email());
        
    //         assertThat(result).isNotNull();
    //     });
    // }

    // @Test
    // void devePermitirBuscarRestaurantePorNome() {
    //     assertDoesNotThrow(() -> {
    //         var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
    //         var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

    //         Restaurante result = restauranteService.buscarRestaurantePorNome(restauranteInsert.nome());
        
    //         assertThat(result).isNotNull();
    //     });
    // }

    // @Test
    // void devePermitirDeletarRestaurante() {
    //     assertDoesNotThrow(() -> {
    //         var restaurante = TestHelper.restauranteDTO(TestHelper.criarRestauranteTeste());
    //         var restauranteInsert = restauranteService.inserirRestaurante(restaurante);

    //         restauranteService.excluirRestaurante(restauranteInsert.id());
            
    //         assertThrows(ResourceNotFoundException.class, () -> {
    //             restauranteService.buscarRestaurante(restauranteInsert.id());
    //         });   

    //     });

    // }
}
