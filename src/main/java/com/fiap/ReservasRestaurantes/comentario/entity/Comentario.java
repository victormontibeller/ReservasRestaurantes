package com.fiap.ReservasRestaurantes.comentario.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "comentario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Comentario {
        @Id
    @Column(unique = true)
    private Long id; 

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente; 
    
}
