package com.fiap.ReservasRestaurantes.mesa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Mesa {
    @Id
    @Column(unique = true)
    private long id; 

    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    @Column(name = "qtd_lugares", nullable = false)
    private int qtdLugares;

    @Column
    private int status; // 0 - liberada; 1 - reservada
    
}
