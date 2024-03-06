package com.fiap.ReservasRestaurantes.horario.entity.enumerations;

public enum TurnoEnum { 
    CAFE("C"),   
    ALMOCO("A"),
    JANTAR("J");    

    private final String codigo;

    TurnoEnum(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
