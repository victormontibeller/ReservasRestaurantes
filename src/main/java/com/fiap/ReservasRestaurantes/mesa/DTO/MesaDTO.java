package com.fiap.ReservasRestaurantes.mesa.DTO;

import java.util.UUID;

import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.PosicaoMesaEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.StatusOcupacaoMesa;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;

public record MesaDTO(
        long id,
        String numero,
        Restaurante restaurante,
        int qtdLugares,
        StatusOcupacaoMesa status,
        PosicaoMesaEnum posicao) {
}
