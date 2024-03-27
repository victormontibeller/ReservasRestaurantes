package com.fiap.ReservasRestaurantes.cliente.DTO;

import java.time.LocalDate;

import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;

public record ClienteDTO(
        long id,
        String nome,
        String email,
        LocalDate dataCadastro,
        Endereco endereco) {
                
        /**
         * Creates a ClienteDTO object with the given parameters.
         *
         * @param  id           the unique identifier of the ClienteDTO
         * @param  nome         the name of the ClienteDTO
         * @param  email        the email of the ClienteDTO
         * @param  dataCadastro the registration date of the ClienteDTO
         * @param  endereco     the address of the ClienteDTO
         * @return              the created ClienteDTO object
         */
        // public static ClienteDTO criarClienteDTO(long id, String nome, String email, LocalDate dataCadastro, Endereco endereco) {
        //         return new ClienteDTO(id, nome, email, dataCadastro, endereco);
        // }


        }
