package com.fiap.ReservasRestaurantes.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.repository.ClienteRepository;
import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // add
    @SuppressWarnings("null")
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
    @SuppressWarnings("null")
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
        Cliente Cliente = new Cliente();
        Cliente.setId(ClienteDTO.id());
        Cliente.setNome(ClienteDTO.nome());
        Cliente.setDataCadastro(ClienteDTO.dataCadastro());
        Cliente.setEndereco(Cliente.getEndereco());

        return Cliente;
    }

    // Métodos do negócio da classe
    public Optional<ReservaDTO> solicitarReserva(long id, long restauranteId){
        return null;        
    } 

    public boolean solicitarCancelarReserva(long reservaId){
        return false;        
    }

    public ComentarioDTO enviarComentario(long id, ComentarioDTO comentarioDTO){        
        return comentarioDTO;
    }
}
