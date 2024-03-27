package com.fiap.ReservasRestaurantes.mesa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Mesa findByNumero(String numero);

    List<Mesa> findByRestauranteAndReservaDataReserva(Restaurante restaurante, LocalDate data);

    List<Mesa> findByRestauranteAndReservaIsNullAndReservaDataReserva(Restaurante restaurante, LocalDate dataReserva);

}