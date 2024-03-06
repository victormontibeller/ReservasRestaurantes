package com.fiap.ReservasRestaurantes.mesa.entity.enumerations;

public enum PosicaoMesaEnum {
    INTERNA("INT"),
    EXTERNA("EXT"),
    TERRACO("TER");

    private final String codigo;

    PosicaoMesaEnum(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
