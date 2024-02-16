package com.fiap.ReservasRestaurantes.reserva.entity.enumerations;

public enum StatusReservaEnum {
    ATIVO("0"),   
    EXPIRADO("1"),
    EXCLUIDO("2");    

    private final String codigo;

    StatusReservaEnum(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
    
}
