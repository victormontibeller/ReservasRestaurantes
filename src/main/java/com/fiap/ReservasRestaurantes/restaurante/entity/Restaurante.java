package com.fiap.ReservasRestaurantes.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @Column(unique = true)
    private Long id; 

    @Column(name = "nome", nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    private Horario horario;

    @OneToMany
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;
}