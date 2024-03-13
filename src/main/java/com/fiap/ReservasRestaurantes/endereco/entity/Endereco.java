package com.fiap.ReservasRestaurantes.endereco.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco {
 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true)
   private UUID id; 

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
   private String cep;

}
