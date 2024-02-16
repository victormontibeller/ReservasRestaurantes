package com.fiap.ReservasRestaurantes.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // add
    public ClienteDTO inserirCliente(ClienteDTO ClienteDTO) {
        Cliente Cliente = toEntity(ClienteDTO);

        // Salva o novo Modelo no repositório
        Cliente = clienteRepository.save(Cliente);

        // Retorna o novo modelo
        return toDTO(Cliente);
    }

    // read all
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    // read
    public Optional<Cliente> buscarCliente(long id) {
        return clienteRepository.findById(id);
    }

    // delete
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
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
