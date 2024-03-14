package com.fiap.ReservasRestaurantes.cliente.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // add
    public ClienteDTO inserirCliente(ClienteDTO clienteDTO) throws ResourceNotFoundException {
        Cliente cliente = toEntity(clienteDTO);

        // Salva o novo Modelo no repositório
        Cliente clienteDB = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteDB != null) {
            throw new ResourceNotFoundException(
                    "Email " + cliente.getEmail() + " já está cadastrado para o cliente de Id=" + clienteDB.getId()
                            + ".");
        }
        cliente = clienteRepository.save(cliente);

        try {
            cliente = clienteRepository.save(cliente);
        } catch (DataAccessException ex) {
            new ResourceNotFoundException("Ocorreu um problema ao tentar salvar o cliente");
        }

        // Retorna o novo modelo
        return toDTO(cliente);
    }

    // read all
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    // read
    public Cliente buscarCliente(long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para este id :: " + id));
        return cliente;
    }

    // read by email
    public Cliente buscarClientePorEmail(String email) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente com o email " + email + " não encontrado.");
        }
        return cliente;
    }

    // read by nome
    public Cliente buscarClientePorNome(String nome) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findByNome(nome);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente com o nome " + nome + " não encontrado.");
        }
        return cliente;
    }

    // read by parte do nome
    public List<Cliente> buscarClientePorParteDoNome(String parteDoNome) {
        return clienteRepository.findByNomeContainingIgnoreCase(parteDoNome);
    }

    // delete
    public String excluirCliente(long id) throws ResourceNotFoundException {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para este id :: " + id));
            
            clienteRepository.deleteById(cliente.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cliente não encontrado para este id :: " + id);
        }
        return "Cliente excluído com sucesso!";
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getDataCadastro(),
                cliente.getEndereco());
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        // Convertendo ClienteDTO para Cliente
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.id());
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setDataCadastro(clienteDTO.dataCadastro());
        cliente.setEndereco(clienteDTO.endereco());

        return cliente;
    }

    // lista reservas em uma data
    public List<Reserva> listaReservas() {

        return null;

    }

    // lista reservas em uma data
    public List<Reserva> listaReservasPorRestaurante() {

        return null;

    }

    // lista reservas em uma data
    public List<Reserva> listaReservasPorRestaurantePorData() {

        return null;

    }
}
