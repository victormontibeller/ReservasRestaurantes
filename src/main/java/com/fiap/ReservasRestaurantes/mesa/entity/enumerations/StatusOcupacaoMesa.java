package com.fiap.ReservasRestaurantes.mesa.entity.enumerations;

import lombok.Getter;

@Getter
public enum StatusOcupacaoMesa {

    DISPONIVEL("DISPONIVEL"),
    OCUPADA("OCUPADA");

    private final String status;

    StatusOcupacaoMesa(String status){
        this.status = status;
    }
}
