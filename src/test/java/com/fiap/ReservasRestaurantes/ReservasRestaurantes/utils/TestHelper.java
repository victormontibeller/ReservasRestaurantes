package com.fiap.ReservasRestaurantes.ReservasRestaurantes.utils;

import java.time.LocalDate;
import java.util.List;

import com.fiap.ReservasRestaurantes.cliente.DTO.ClienteDTO;
import com.fiap.ReservasRestaurantes.cliente.entity.Cliente;
import com.fiap.ReservasRestaurantes.comentario.DTO.ComentarioDTO;
import com.fiap.ReservasRestaurantes.comentario.entity.Comentario;
import com.fiap.ReservasRestaurantes.endereco.DTO.EnderecoDTO;
import com.fiap.ReservasRestaurantes.endereco.entity.Endereco;
import com.fiap.ReservasRestaurantes.horario.DTO.HorarioDTO;
import com.fiap.ReservasRestaurantes.horario.entity.Horario;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.DiaSemanaEnum;
import com.fiap.ReservasRestaurantes.horario.entity.enumerations.TurnoEnum;
import com.fiap.ReservasRestaurantes.mesa.DTO.MesaDTO;
import com.fiap.ReservasRestaurantes.mesa.entity.Mesa;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.PosicaoMesaEnum;
import com.fiap.ReservasRestaurantes.mesa.entity.enumerations.StatusOcupacaoMesa;
import com.fiap.ReservasRestaurantes.reserva.DTO.ReservaDTO;
import com.fiap.ReservasRestaurantes.reserva.entity.Reserva;
import com.fiap.ReservasRestaurantes.reserva.entity.enumerations.StatusReservaEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.Restaurante;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.StatusRestauranteEnum;
import com.fiap.ReservasRestaurantes.restaurante.entity.enumerations.TipoCozinhaEnum;

public class TestHelper {  

    /**
     * Creates and returns a test client with predefined information.
     *
     * @return         the test client created
     */    
    public static Cliente criarClienteTeste() {
        Cliente cliente = new Cliente();
        cliente.setId(0);
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setEndereco(new Endereco(0L, 
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000"));
        return cliente;
    }

    public static Cliente criarClienteTeste1() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Paulinho");
        cliente1.setEmail("Paulinho@gmail.com");
        cliente1.setDataCadastro(LocalDate.now());
        cliente1.setEndereco(new Endereco(1L, 
                                      "rua abc1",     
                                   1233,
                                   "Centro1", 
                                   "Caramuru",
                                   "XX", 
                                     "Brasil",
                                      "00000-000"));
        return cliente1;
    }

    public static ClienteDTO clienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getDataCadastro(),
                cliente.getEndereco());
    }

    
    /**
     * A function to create and return a new Endereco object.
     *
     * @return         	the newly created Endereco object
     */
    public static Endereco criarEnderecoTeste() {
        return new Endereco(0L,
                        "rua abc",     
                     123,
                     "Centro", 
                     "São Paulo",
                     "SP", 
                       "Brasil", 
                        "00000-000");
    }

    /**
     * A function to create and return a new Endereco object.
     *
     * @return         	the newly created Endereco object
     */
    public static Endereco criarEnderecoTeste1() {
        return new Endereco(0L,
                        "rua def",     
                     123,
                     "cidade das flores", 
                     "Osasco",
                     "SP", 
                       "Brasil", 
                        "00000-000");
    }

    /**
     * Creates a new EnderecoDTO object based on the provided Endereco object.
     *
     * @param  endereco  the Endereco object to create the EnderecoDTO from
     * @return           the newly created EnderecoDTO object
     */
    public static EnderecoDTO enderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais(),
                endereco.getCep());

    }
    
    /**
     * Cria um horario para os testes.
     *
     * @return  horaRest
     */
    public static Horario criarHorarioTeste() {
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

    public static Horario criarHorarioTeste1() {
        Restaurante restaurante = criarRestauranteTeste();

        Horario horaRest = new Horario(0L,
                                        restaurante,
                                        restaurante.getNome(),
                                        TurnoEnum.JANTAR,
                                        DiaSemanaEnum.SEGUNDA,
                                        LocalDate.now(),
                                        LocalDate.now());
        return horaRest;
    }

    /**
     * Generates a HorarioDTO object based on the provided Horario object.
     *
     * @param  horario    the Horario object to convert
     * @return            a new HorarioDTO object with mapped attributes
     */
    public static HorarioDTO horarioDTO(Horario horario) {
    return new HorarioDTO(
            horario.getId(),
            null,
            horario.getNome(),
            horario.getAlmocoJantar(),
            horario.getDiaSemana(),
            horario.getInicioHorario(),
            horario.getFimHorario());
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
    public static Reserva criarReservaTeste() {
        List<Mesa> mesas = List.of();
        return new Reserva(1L,
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

    /**
     * Creates a test reservation.
     *
     * @return         the test reservation
     */
    public static Reserva criarReservaTeste1() {
        List<Mesa> mesas = List.of();
        return new Reserva(2L,
                    criarClienteTeste1(),
                    criarRestauranteTeste(),
                    mesas,
                    2,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now(),
                    15,
                    LocalDate.now(),
                    StatusReservaEnum.ATIVO);
    }
    
    /**
     * Creates a ReservaDTO object based on the provided Reserva object.
     *
     * @param  reserva  the Reserva object containing the data for the ReservaDTO
     * @return          the created ReservaDTO object
     */
    public static ReservaDTO reservaDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getCliente(),
                reserva.getRestaurante(),
                reserva.getMesa(),
                reserva.getNumPessoas(),
                reserva.getDataReserva(),
                reserva.getDataCriacao(),
                reserva.getInicioReserva(),
                reserva.getToleranciaMinutos(),
                reserva.getHorarioLimite(),
                reserva.getStatus());
    }


    /**
     * Creates a test Mesa object.
     *
     * @return         the test Mesa object created
     */
    public static Mesa criarMesaTeste() {

        return new Mesa(1L,
                        "1",
                        criarRestauranteTeste(),
                        criarReservaTeste(),
                        64,
                        StatusOcupacaoMesa.OCUPADA,
                        PosicaoMesaEnum.INTERNA);
    }

    /**
     * Creates a test Mesa object with specific attributes.
     *
     * @return         the Mesa object created for testing purposes
     */
    public static Mesa criarMesaTeste1() {
        return new Mesa(1L,
                        "2",
                        criarRestauranteTeste(),
                        criarReservaTeste(),
                        4,
                        StatusOcupacaoMesa.OCUPADA,
                        PosicaoMesaEnum.INTERNA);
    }

    /**
     * Converts a Mesa object to a MesaDTO object.
     *
     * @param  Mesa  the Mesa object to convert
     * @return       a MesaDTO object
     */
    public static MesaDTO mesaDTO(Mesa Mesa) {
        return new MesaDTO(
                Mesa.getId(),
                Mesa.getNumero(),
                Mesa.getRestaurante(),
                Mesa.getQtdLugares(),
                Mesa.getStatus(),
                Mesa.getPosicao());
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

    /**
     * A method to create a test comment.
     *
     * @return         	the created test comment
     */
    public static Comentario criarComentarioTeste1(){

        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setCliente(TestHelper.criarClienteTeste1());
        comentario.setTitulo("Restaurante ruim");
        comentario.setTexto("não servia pao de alho");
        comentario.setAvaliacao(1);
        comentario.setDataCriacao(LocalDate.now());

        return comentario;
    }

    /**
     * Converts a Comentario object to a ComentarioDTO object.
     *
     * @param  comentario   the Comentario object to convert
     * @return              the converted ComentarioDTO object
     */
    public static ComentarioDTO toDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getCliente(),
                comentario.getTitulo(),
                comentario.getTexto(),
                comentario.getAvaliacao(),
                comentario.getDataCriacao());
    }

    
}
