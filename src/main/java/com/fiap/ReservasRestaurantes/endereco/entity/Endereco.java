package com.fiap.ReservasRestaurantes.endereco.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco {
   @Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true)
   private long id;

   @Column(nullable = false)
   private String rua;

   @Column(nullable = false)
   private int numero;

   @Column(nullable = false)
   private String bairro;

   @Column(nullable = false)
   private String cidade;

   @Column(nullable = false)
   private String estado;

   @Column(nullable = false)
   private String pais;

   @Column(nullable = false)
   private int cep;

}
