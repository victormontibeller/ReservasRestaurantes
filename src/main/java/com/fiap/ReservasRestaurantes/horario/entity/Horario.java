package com.fiap.ReservasRestaurantes.horario.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "horario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Horario {
    @Id
    @Column(unique = true)
    private long id; 

    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
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