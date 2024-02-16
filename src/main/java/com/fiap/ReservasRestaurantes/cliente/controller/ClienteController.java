package com.fiap.ReservasRestaurantes.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.fiap.ReservasRestaurantes.cliente.controller.ClienteController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes() {
        return ResponseEntity.ok().body(clienteService.buscarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> buscarCliente(@PathVariable long id) {
        return ResponseEntity.ok().body(clienteService.buscarCliente(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteSalva = clienteService.inserirCliente(clienteDTO);

        return new ResponseEntity<>(clienteSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable long id) {
        try {
            clienteService.excluirCliente(id);
            LOGGER.info("Cliente {} excluido com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível excluir o cliente {}!", id);
            return new ResponseEntity<>("Não foi possível excluir o cliente!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Cliente excluido com sucesso!", HttpStatus.OK);
    }
}
