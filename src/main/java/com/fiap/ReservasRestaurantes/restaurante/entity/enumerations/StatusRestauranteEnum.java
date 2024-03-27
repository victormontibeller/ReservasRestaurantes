package com.fiap.ReservasRestaurantes.restaurante.entity.enumerations;

public enum StatusRestauranteEnum {
    ATIVO("0"),   
    SUSPENSO("1"),
    EXCLUIDO("2");    

    private final String codigo;

    StatusRestauranteEnum(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
