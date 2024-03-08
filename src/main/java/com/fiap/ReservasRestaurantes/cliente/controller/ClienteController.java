package com.fiap.ReservasRestaurantes.cliente.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.cliente.service.ClienteService;
import com.fiap.ReservasRestaurantes.excecoes.ResourceNotFoundException;
import com.fiap.ReservasRestaurantes.cliente.controller.ClienteController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes() {
        List<Cliente> clientes = clienteService.buscarClientes();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarCliente(id));
    }

    @GetMapping("/{email}/email")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorEmail(email));
    }

    @GetMapping("/{nome}/nome")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable String nome) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorNome(nome));
    }

    @GetMapping("/{parteDoNome}/parteDoNome")
    public ResponseEntity<List<Cliente>> buscarClientePorParteDoNome(@PathVariable String parteDoNome)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorParteDoNome(parteDoNome));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody ClienteDTO clienteDTO)
            throws ResourceNotFoundException {
        ClienteDTO clienteSalva = clienteService.inserirCliente(clienteDTO);

        return new ResponseEntity<>(clienteSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable UUID id) throws ResourceNotFoundException {
        String msg = clienteService.excluirCliente(id);
        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
