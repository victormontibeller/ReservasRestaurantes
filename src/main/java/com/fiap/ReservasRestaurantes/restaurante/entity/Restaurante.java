package com.fiap.ReservasRestaurantes.restaurante.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "restaurante")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Restaurante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id; 

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)   
    List<Horario> horario;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)   
    List<Reserva> reserva;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)   
    List<Mesa> mesa;

    @Column
    private TipoCozinhaEnum tipoCozinha;

    @Column
    private int capacidade;

    @Column
    private StatusRestauranteEnum status;

    @Column(nullable = false, columnDefinition = "DATE")
    LocalDate dataCadastro;
}