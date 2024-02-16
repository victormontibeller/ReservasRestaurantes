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
    private long id; 

    //@NotBlank
    @Column
    private String nome;

    //@NotBlank
    @Column
    private LocalDate dataCadastro;

    @Embedded
    private Endereco endereco;
}