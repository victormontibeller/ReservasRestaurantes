package com.fiap.ReservasRestaurantes.horario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> { 

}