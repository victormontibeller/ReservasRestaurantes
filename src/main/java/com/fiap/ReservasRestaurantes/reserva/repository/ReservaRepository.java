package com.fiap.ReservasRestaurantes.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{   

    
}
