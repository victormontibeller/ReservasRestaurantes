package com.fiap.ReservasRestaurantes.cliente.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> { 
    Cliente findByEmail(String email);

    Cliente findByNome(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(String parteDoNome);
}
