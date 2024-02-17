package com.fiap.ReservasRestaurantes.cliente.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // add
    public ClienteDTO inserirCliente(ClienteDTO ClienteDTO) {
        Cliente cliente = toEntity(ClienteDTO);

        // Salva o novo Modelo no repositório
        try {
            cliente = clienteRepository.save(cliente);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o endereço");
        } catch (ConstraintViolationException ex) {
            new ResourceNotFoundException("Endereço já cadastrado");
        }

        // Retorna o novo modelo
        return toDTO(cliente);
    }

    // read all
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    // read
    public Cliente buscarCliente(Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para este id :: " + id));
        return cliente;
    }

    // delete
    public String excluirCliente(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            new ResourceNotFoundException("Cliente não encontrado para este id :: " + id);
        }
        return "Cliente excluído com sucesso!";
    }

    public ClienteDTO toDTO(Cliente Cliente) {
        return new ClienteDTO(
                Cliente.getId(),
                Cliente.getNome(),
                Cliente.getDataCadastro(),
                Cliente.getEndereco());
    }

    public Cliente toEntity(ClienteDTO ClienteDTO) {
        // Convertendo ClienteDTO para Cliente
        Cliente cliente = new Cliente();
        cliente.setId(ClienteDTO.id());
        cliente.setNome(ClienteDTO.nome());
        cliente.setDataCadastro(ClienteDTO.dataCadastro());
        cliente.setEndereco(ClienteDTO.endereco());

        return cliente;
    }

}
