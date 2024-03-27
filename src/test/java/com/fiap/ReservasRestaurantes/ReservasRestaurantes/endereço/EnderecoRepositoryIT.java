package com.fiap.ReservasRestaurantes.ReservasRestaurantes.endereço;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils.TestHelper;
import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.endereco.repository.EnderecoRepository;
import com.fiap.ReservasRestaurantes.endereco.service.EnderecoService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class EnderecoRepositoryIT {
    
    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoService service;

    /**
     * Test case to verify if the table can be created.
     *
     * @return void
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> {
            var totalRegistros = repository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    /**
     * Test case to verify if a new address can be inserted.
     * @throws ResourceNotFoundException 
     *
     */
    @Test
    void devePermitirInserirNovoEendereco() throws ResourceNotFoundException {
        var novoEndereco = TestHelper.enderecoDTO(TestHelper.criarEnderecoTeste());
        var novoEnderecoSalvo = service.inserirEndereco(novoEndereco);
        
        assertThat(novoEnderecoSalvo).isNotNull().isInstanceOf(EnderecoDTO.class);
        assertThat(novoEnderecoSalvo.id()).isNotNull();
        assertThat(novoEnderecoSalvo.cep()).isNotNull();
        assertThat(novoEnderecoSalvo.rua()).isNotNull();
    }

    void devePermitirBuscarEenderecoPorId() throws ResourceNotFoundException {
        var novoEndereco = TestHelper.enderecoDTO(TestHelper.criarEnderecoTeste());

        var novoEnderecoSalvo = service.inserirEndereco(novoEndereco);
        var novoEnderecoBuscado = service.buscarEndereco(novoEnderecoSalvo.id());

        assertThat(novoEnderecoBuscado).isNotNull();
        assertThat(novoEnderecoBuscado.getId()).isEqualTo(novoEnderecoSalvo.id());

    }

    /**
     * Deve permitir buscar todos os endereços.
     * @throws ResourceNotFoundException 
     *
     */
    @Test
     void devePermitirBuscarTodosOsEenderecos() throws ResourceNotFoundException {
        var novoEndereco = TestHelper.enderecoDTO(TestHelper.criarEnderecoTeste());
        service.inserirEndereco(novoEndereco);

        var novoEndereco1 = TestHelper.enderecoDTO(TestHelper.criarEnderecoTeste1());
        service.inserirEndereco(novoEndereco1);

        List<Endereco> enderecos = service.buscarEnderecos();

        assertNotNull(enderecos);
        assertThat(enderecos).hasSize(2);
        assertThat(enderecos).isNotEmpty();
    }

    void devePermitirBuscarEenderecosPorEmail() {

        fail("Teste ainda não implementado");
    }

    /**
     * Deletes an address and tests if the address is successfully deleted.
     *
     * @throws ResourceNotFoundException if the address is not found
     */
    @Test
    void devePermitirDeletarEendereco() throws ResourceNotFoundException {
        var novoEndereco = TestHelper.enderecoDTO(TestHelper.criarEnderecoTeste());
        service.inserirEndereco(novoEndereco);

        assertThrows(ResourceNotFoundException.class, () -> { 
            service.excluirEndereco(novoEndereco.id());
        });
    }
    
}
