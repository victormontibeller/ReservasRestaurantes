package com.fiap.ReservasRestaurantes.horario.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "horario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Horario {
    @Id
    @Column(unique = true)
    private long id; 

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TurnoEnum almocoJantar;

    @Enumerated(EnumType.STRING)
    private DiaSemanaEnum diaSemana;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate inicioHorario;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate fimHorario;
}