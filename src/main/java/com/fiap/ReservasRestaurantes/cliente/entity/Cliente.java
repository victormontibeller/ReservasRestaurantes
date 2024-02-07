package com.fiap.ReservasRestaurantes.cliente.entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cliente {
    @Id
    @Column(unique = true)
    private Long id; 

    @Column(nullable = true)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Embedded
    private Endereco endereco;
}