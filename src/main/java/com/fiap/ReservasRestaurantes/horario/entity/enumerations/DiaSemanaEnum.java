package com.fiap.ReservasRestaurantes.horario.entity.enumerations;

public enum DiaSemanaEnum {
    DOMINGO("DOM"),
    SEGUNDA("SEG"),
    TERCA("TER"),
    QUARTA("QUA"),
    QUINTA("QUI"),
    SEXTA("SEX"),
    SABADO("SAB");

    private final String abreviado;
    
    DiaSemanaEnum(String abreviado) {
        this.abreviado = abreviado;
    }

    public String getAbreviado() {
        return abreviado;
    }
    
}

    
