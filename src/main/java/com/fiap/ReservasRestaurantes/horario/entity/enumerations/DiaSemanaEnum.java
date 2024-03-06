package com.fiap.ReservasRestaurantes.horario.entity.enumerations;

public enum DiaSemanaEnum {
    DOMINGO(1),
    SEGUNDA(2),
    TERCA(3),
    QUARTA(4),
    QUINTA(5),
    SEXTA(6),
    SABADO(7);

    private final int codigo;
    
    DiaSemanaEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
}

    
