package com.api.juegos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CrearPartidaDTO {



    private Long jugadorId;

    private Long juegoId;


    private String palabra;

    private String dificultad;

    private Long intentos;

    private Long puntos;



}
