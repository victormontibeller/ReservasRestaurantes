package com.fiap.ReservasRestaurantes.mesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> { 

}