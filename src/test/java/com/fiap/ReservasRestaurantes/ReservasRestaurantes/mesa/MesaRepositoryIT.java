package com.fiap.ReservasRestaurantes.ReservasRestaurantes.mesa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.mesa.DTO.MesaDTO;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.repository.MesaRepository;
import com.fiap.ReservasRestaurantes.mesa.service.MesaService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class MesaRepositoryIT {
    
    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private MesaService mesaService;

    /**
     * A method to test if it allows creating a table.
     *
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = mesaRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    /**
     * A test to verify the insertion of a new table.
     *
     * @throws ResourceNotFoundException
     */
    @Test
    void devePermitirInserirNovaMesa() throws ResourceNotFoundException {
        var mesa = TestHelper.mesaDTO(TestHelper.criarMesaTeste());
        var mesaSalva = mesaService.inserirMesa(mesa);

        assertThat(mesaSalva)
                   .isNotNull()
                   .isInstanceOf(MesaDTO.class);

        assertThat(mesa.status()).isEqualTo(mesaSalva.status());
        assertThat(mesa.numero()).isEqualTo(mesaSalva.numero());
        assertThat(mesa.posicao()).isEqualTo(mesaSalva.posicao());
    }

    /**
     * Test case for the method that allows searching for a table by ID.
     *
     * @throws ResourceNotFoundException if the resource is not found
     */
    @Test
    void devePermitirBuscarMesaPorId() throws ResourceNotFoundException {
        var mesa = TestHelper.criarMesaTeste();
        var mesaSalva = mesaService.inserirMesa(TestHelper.mesaDTO(mesa));
        var musaBuscada = mesaService.buscarMesa(mesaSalva.id());
        
        assertThat(musaBuscada).isNotNull();
        assertThat(musaBuscada.getId()).isEqualTo(mesaSalva.id());
    }

    // @Test
    // void devePermitirBuscarTodasAsMesas() {
    //     assertDoesNotThrow(() -> {
    //         var mesa = TestHelper.criarMesaTeste();
    //         mesaService.inserirMesa(TestHelper.mesaDTO(mesa));

    //         var mesa1 = TestHelper.criarMesaTeste1();
    //         mesaService.inserirMesa(TestHelper.mesaDTO(mesa1));

    //         List<Mesa> mesas = mesaService.buscarMesas();

    //         assertThat(mesas).isNotNull();
    //         assertThat(mesas).isNotEmpty();
    //     });
    // }

    // @Test
    // void devePermitirBuscarMesasPorNumero() {
    //     assertDoesNotThrow(() -> {
    //         var mesa = TestHelper.criarMesaTeste();
    //         mesaService.inserirMesa(TestHelper.mesaDTO(mesa));
            
    //         var mesaBuscadaNumero = mesaService.buscarMesaPorNumero(mesa.getNumero());

    //         assertThat(mesaBuscadaNumero).isNotNull();
    //         assertEquals(mesa.getNumero(), mesaBuscadaNumero.getNumero());
    //     });
    // }

    
    // @Test
    // void devePermitirDeletarMesa() {
    //     assertDoesNotThrow(() -> {
    //         var mesa = TestHelper.criarMesaTeste();
    //         mesaService.inserirMesa(TestHelper.mesaDTO(mesa));

    //         mesaService.excluirMesa(mesa.getId());

    //         assertThrows(ResourceNotFoundException.class, () -> {
    //             mesaService.buscarMesa(mesa.getId());
    //         });
    //     });
    // }
    
}
