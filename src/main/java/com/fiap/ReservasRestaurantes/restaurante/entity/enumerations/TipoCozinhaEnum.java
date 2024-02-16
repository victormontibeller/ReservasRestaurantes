package com.fiap.ReservasRestaurantes.restaurante.entity.enumerations;

public enum TipoCozinhaEnum {
    JAPONESA("JP"),
    ITALIANA("IT"),
    CHINESA("CN"),
    BRASILEIRA("BR"),
    INDIANA("IN"),
    TAILANDESA("TH"),
    PERUANA("PE"),
    FRANCESA("FR"),
    ORIENTAL("ORI"),
    MEDITERRANEA("MED"),
    INTERNACIONAL("INT");

    private final String sigla;

    TipoCozinhaEnum(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}