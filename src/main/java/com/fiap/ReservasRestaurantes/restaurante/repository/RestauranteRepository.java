package com.fiap.ReservasRestaurantes.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Restaurante findByEmail(String email);

    Restaurante findByNome(String nome);

    List<Restaurante> findByNomeContainingIgnoreCase(String parteDoNome);

    @Query("SELECT r FROM Restaurante r WHERE r.endereco.cidade = :cidade")
    List<Restaurante> findByCidade(String cidade);

    @Query("SELECT r FROM Restaurante r WHERE r.endereco.cidade = :cidade AND r.endereco.bairro = :bairro")
    List<Restaurante> findByCidadeAndBairro(String cidade, String bairro);

    List<Restaurante> findByTipoCozinha(TipoCozinhaEnum tipoCozinha);

    //@Query("SELECT r FROM Restaurante r WHERE r.horario.dia_semana = :horario")
    //List<Restaurante> findByHorario(String horario);
}