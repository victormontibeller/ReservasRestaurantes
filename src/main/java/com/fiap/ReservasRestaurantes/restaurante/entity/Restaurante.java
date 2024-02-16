package com.fiap.ReservasRestaurantes.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
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
@Table(name = "restaurante")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Restaurante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id; 

    @Column(name = "nome", nullable = false)
    private String nome;

    //@Embedded
    //private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    //@OneToOne
    //@JoinColumn(name = "horario_id", referencedColumnName = "id")
    //private Horario horario;

    @Column
    private int capacidade;
}