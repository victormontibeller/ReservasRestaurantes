package com.fiap.ReservasRestaurantes.comentario.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, UUID> { 

}