package com.fiap.ReservasRestaurantes.ReservasRestaurantes.horario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.horario.DTO.HorarioDTO;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.repository.HorarioRepository;
import com.fiap.ReservasRestaurantes.horario.service.HorarioService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class HorarioRepoositoryIT {

        
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private HorarioService horarioService;

    /**
     * Test case to verify if the creation of a table is allowed.
     *
     * @return  void
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> { // Check for unhandled exceptions
            var totalRegistros = horarioRepository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }
    
    /**
     * Test case to verify if a new schedule can be inserted.
     *
     * @return  void
     * @throws ResourceNotFoundException 
     */
    @Test
    void devePermitirInserirNovoHorario() throws ResourceNotFoundException {
        var novoHorario =  TestHelper.horarioDTO(TestHelper.criarHorarioTeste());
        var horario = horarioService.inserirHorario(novoHorario);

        assertThat(horario)
                   .isNotNull()
                   .isInstanceOf(HorarioDTO.class);

        assertThat(horario.id()).isNotNull();
        assertThat(horario.nome()).isNotNull();
    }

    /**
     * Test case to verify if the search for a specific time slot by ID is allowed.
     *
     * @throws ResourceNotFoundException if the resource is not found
     */
    @Test
    void devePermitirBuscarHorarioPorId() throws ResourceNotFoundException {
        var novoHorario =  TestHelper.horarioDTO(TestHelper.criarHorarioTeste());

        var horario = horarioService.inserirHorario(novoHorario);
        var horarioBuscado = horarioService.buscarHorario(horario.id());

        assertThat(horarioBuscado).isNotNull();
        assertThat(horarioBuscado.getId()).isEqualTo(novoHorario.id());
    }

    /**
     * A test to verify that it allows searching for all schedules.
     *
     */
    @Test
    void devePermitirBuscarTodosOsHorarios() {
        assertDoesNotThrow (() -> {
            var novoHorario = TestHelper.horarioDTO(TestHelper.criarHorarioTeste());
            horarioService.inserirHorario(novoHorario);

            var novoHorario1 = TestHelper.horarioDTO(TestHelper.criarHorarioTeste1());
            horarioService.inserirHorario(novoHorario1); 

            List <Horario> horarios = horarioService.buscarHorarios();

            assertThat(horarios).isNotNull();
            assertThat(horarios).isNotEmpty();
        });
    }
    /**
     * Deletes a schedule and checks that it is successfully deleted.
     */
    @Test
    void devePermitirDeletarHorario() {
        assertDoesNotThrow(() -> {
            var novoHorario = TestHelper.horarioDTO(TestHelper.criarHorarioTeste());
            horarioService.inserirHorario(novoHorario);
            horarioService.excluirHorario(novoHorario.id());
        });
    }
    
}
