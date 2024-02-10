package com.fiap.ReservasRestaurantes.reserva.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;

import jakarta.persistence.Column;
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
@Table(name = "reserva")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Reserva {

    @Id
    @Column(unique = true)
    private Long id; 

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;

    @Column
    private int numPessoas;

    @Column
    private LocalDate dataReserva;

    @Column
    private LocalDate dataCriacao;

    @Column
    private LocalDate inicioReserva;

    @Column
    private int toleranciaMinutos;

    @Column
    private LocalDate horarioLimite;
    
}
