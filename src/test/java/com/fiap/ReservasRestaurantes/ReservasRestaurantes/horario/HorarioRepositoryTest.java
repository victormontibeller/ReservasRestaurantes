package com.fiap.ReservasRestaurantes.ReservasRestaurantes.horario;

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
import com.fiap.ReservasRestaurantes.horario.repository.HorarioRepository;
import com.fiap.ReservasRestaurantes.horario.service.HorarioService;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

class HorarioRepositoryTest {

    @Mock
    private HorarioRepository horarioRepository;

    @InjectMocks
    private HorarioService horarioService;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * Teste para inserir um horário com sucesso.
     */
    @Test
    void testeInserirHorarioSucesso() {
        Horario horario = criarHorarioSuporte();
        when(horarioRepository.save(horario)).thenReturn(horario);

        var result = horarioRepository.save(horario);
        
        // Validações
        assertNotNull(horario, "O horário não pode ser nulo");
        assertEquals(horario, result, "O resultado deve ser o mesmo que o horário inserido");
        assertEquals(horario.getId(), result.getId(), "O ID do horário deve ser o mesmo que o ID do horário inserido");
        verify(horarioRepository, times(1)).save(horario);
    }


    @Test
    void TesteBuscarHorarioPorId() {
        Horario horario = criarHorarioSuporte();

        when(horarioRepository.save(horario)).thenReturn(horario);
        when(horarioRepository.findById(horario.getId())).thenReturn(Optional.of(horario));
    
        var result = horarioRepository.findById(horario.getId());

        assertTrue(horarioRepository.findById(horario.getId()).isPresent());
        assertEquals(horario, result.get());
        assertThat(horario.getId()).isEqualTo(result.get().getId());
    }

    /**
     * teste para deletar um horário por id
     */
    @Test
    void TesteDeletarHorarioPorId() {
        Horario horario = criarHorarioSuporte();
        when(horarioRepository.save(horario)).thenReturn(horario);
        
        horarioRepository.delete(horario);

        verify(horarioRepository, times(1)).delete(horario);
    }

    /**
     * Cria um horario para os testes.
     *
     * @return  horaRest
     */
    Horario criarHorarioSuporte() {
        Restaurante restaurante = criarRestauranteTeste();

        Horario horaRest = new Horario(0L,
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
        Endereco endereco = new Endereco(0L, 
                                     "rua abc",     
                                  123,
                                  "Centro", 
                                  "São Paulo",
                                  "SP", 
                                    "Brasil", 
                                     "00000-000");
                                     
        Restaurante restaurante = new Restaurante(0L,
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
