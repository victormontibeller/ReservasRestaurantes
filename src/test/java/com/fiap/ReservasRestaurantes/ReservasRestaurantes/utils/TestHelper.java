package com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils;

import java.time.LocalDate;
import java.util.List;

import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.PosicaoMesaEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.StatusOcupacaoMesa;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

public class TestHelper {  

    public static Cliente criarClienteTeste() {
        Cliente cliente = new Cliente();
        cliente.setId(0L/* UUID.randomUUID() */);
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setEndereco(new Endereco(0L/* UUID.randomUUID() */, 
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000"));
        return cliente;
    }

    
    /**
     * A function to create and return a new Endereco object.
     *
     * @return         	the newly created Endereco object
     */
    public static Endereco criarEndereco() {
        return new Endereco(0L,/* UUID.randomUUID(), */
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000");
    }

    
    /**
     * Cria um horario para os testes.
     *
     * @return  horaRest
     */
    public static Horario criarHorarioSuporte() {
        Restaurante restaurante = criarRestauranteTeste();

        Horario horaRest = new Horario(0L,
                                        restaurante,
                                        restaurante.getNome(),
                                        TurnoEnum.ALMOCO,
                                        DiaSemanaEnum.DOMINGO,
                                        LocalDate.now(),
                                        LocalDate.now());
        return horaRest;
    }

    /**
     * Create a test restaurant with empty schedules, reservations, and tables, and a randomly generated address. 
     *
     * @return         	the created restaurant
     */
    public static Restaurante criarRestauranteTeste() {
        List<Horario> horarios = List.of();
        List<Reserva> reservas = List.of();
        List<Mesa> mesas = List.of();
        Endereco endereco = new Endereco(0L, 
                                     "rua abc",     
                                  123,
                                  "Centro", 
                                  "São Paulo",
                                  "SP", 
                                    "Brasil", 
                                     "00000-000");
                                     
        Restaurante restaurante = new Restaurante(0L,
                                                "Dois Irmaos",
                                                endereco,
                                                "doisIrmaos@doisIrmaos.com",
                                                horarios,
                                                reservas,
                                                mesas,
                                                TipoCozinhaEnum.INTERNACIONAL,
                                                10,
                                                StatusRestauranteEnum.ATIVO,
                                                LocalDate.now()); 
                                                    
        return restaurante;
    }

        /**
     * Creates a test reservation.
     *
     * @return         the test reservation
     */
    public static Reserva criaReservaTeste() {
        List<Mesa> mesas = List.of();
        return new Reserva(0L,
                    criarClienteTeste(),
                    criarRestauranteTeste(),
                    mesas,
                    2,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now(),
                    10,
                    LocalDate.now(),
                    StatusReservaEnum.ATIVO);
    }
    public static Mesa createMesaTeste() {

        return new Mesa(0L,
                        "1",
                        criarRestauranteTeste(),
                        null,
                        64,
                        StatusOcupacaoMesa.OCUPADA,
                        PosicaoMesaEnum.INTERNA);
    }

        /**
     * A method to create a test comment.
     *
     * @return         	the created test comment
     */
    public static Comentario criarComentarioTeste(){

        Comentario comentario = new Comentario();
        comentario.setId(0L);
        comentario.setCliente(TestHelper.criarClienteTeste());
        comentario.setTitulo("Restaurante Bom");
        comentario.setTexto("Excelente");
        comentario.setAvaliacao(5);
        comentario.setDataCriacao(LocalDate.now());

        return comentario;
    }
    
}
