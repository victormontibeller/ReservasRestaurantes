package com.fiap.ReservasRestaurantes.reserva.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "reserva")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Reserva {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY) 
    private List<Mesa> mesa;

    @Column
    private int numPessoas;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataReserva;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataCriacao;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate inicioReserva;

    @Column
    private int toleranciaMinutos;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate horarioLimite;

    @Column
    private StatusReservaEnum status; 
    
}
