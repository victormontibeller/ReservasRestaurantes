package com.fiap.ReservasRestaurantes.endereco.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> { 

}