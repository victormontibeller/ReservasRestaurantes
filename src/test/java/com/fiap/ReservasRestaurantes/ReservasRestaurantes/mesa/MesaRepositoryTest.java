package com.fiap.ReservasRestaurantes.ReservasRestaurantes.mesa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.PosicaoMesaEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.StatusOcupacaoMesa;
import com.fiap.ReservasRestaurantes.mesa.repository.MesaRepository;
import com.fiap.ReservasRestaurantes.mesa.service.MesaService;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

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
        
        Mesa mesa = createMesaTeste();
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
        Mesa mesa = createMesaTeste();

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
        Mesa mesa = createMesaTeste();
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
        Mesa mesa = createMesaTeste();
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
        Mesa mesa = createMesaTeste();
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        mesaRepository.delete(mesa);

            verify(mesaRepository, times(1)).delete(mesa);
    }

    /**
     * Creates a test mesa.
     *
     * @return          a Mesa object representing the test mesa
     */
    Mesa createMesaTeste() {

        return new Mesa(UUID.randomUUID(),
                        "1",
                        criarRestauranteTeste(),
                        null,
                        64,
                        StatusOcupacaoMesa.OCUPADA,
                        PosicaoMesaEnum.INTERNA);
    }

    /**
     * Creates and returns a support schedule.
     *
     * @return  the support schedule created
     */
    Horario criarHorarioSuporte() {
        Restaurante restaurante = criarRestauranteTeste();

        Horario horaRest = new Horario(UUID.randomUUID(),
                                        restaurante,
                                        restaurante.getNome(),
                                        TurnoEnum.ALMOCO,
                                        DiaSemanaEnum.DOMINGO,
                                        LocalDate.now(),
                                        LocalDate.now());
        return horaRest;
    }

    /**
     * Create a test restaurant with empty schedules, reservations, and tables, and a randomly generated address. 
     *
     * @return         	the created restaurant
     */
    Restaurante criarRestauranteTeste() {
        List<Horario> horarios = List.of();
        List<Reserva> reservas = List.of();
        List<Mesa> mesas = List.of();
        Endereco endereco = new Endereco(UUID.randomUUID(), 
                                     "rua abc",     
                                  123,
                                  "Centro", 
                                  "São Paulo",
                                  "SP", 
                                    "Brasil", 
                                     "00000-000");
                                     
        Restaurante restaurante = new Restaurante(UUID.randomUUID(),
                                                "Dois Irmaos",
                                                endereco,
                                                "doisIrmaos@doisIrmaos.com",
                                                horarios,
                                                reservas,
                                                mesas,
                                                TipoCozinhaEnum.INTERNACIONAL,
                                                10,
                                                StatusRestauranteEnum.ATIVO,
                                                LocalDate.now()); 
                                                    
        return restaurante;
    }
}
