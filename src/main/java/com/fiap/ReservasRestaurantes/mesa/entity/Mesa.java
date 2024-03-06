package com.fiap.ReservasRestaurantes.mesa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.PosicaoMesaEnum;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "mesa")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    
    @Column
    private String numero; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    private Reserva reserva;

    @Column(name = "qtd_lugares", nullable = false)
    private int qtdLugares;

    @Column
    private int status; // 0 - liberada; 1 - reservada

    @Column
    private PosicaoMesaEnum posicao;
    
}
