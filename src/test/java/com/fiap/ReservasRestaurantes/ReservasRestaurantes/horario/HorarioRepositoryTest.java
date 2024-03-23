package com.fiap.ReservasRestaurantes.ReservasRestaurantes.horario;

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
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.repository.HorarioRepository;
import com.fiap.ReservasRestaurantes.horario.service.HorarioService;

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
        Horario horario = TestHelper.criarHorarioTeste();
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
        Horario horario = TestHelper.criarHorarioTeste();

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
        Horario horario = TestHelper.criarHorarioTeste();
        when(horarioRepository.save(horario)).thenReturn(horario);
        
        horarioRepository.delete(horario);

        verify(horarioRepository, times(1)).delete(horario);
    }

}
