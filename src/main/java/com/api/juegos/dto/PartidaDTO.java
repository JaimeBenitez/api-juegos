package com.api.juegos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PartidaDTO {

    private Long id;

    private LocalDateTime fecha_hora;

    private Long jugadorId;

    private Long juegoId;

    private String jugadorNombre;

    private String juegoNombre;

    private String palabra;

    private String dificultad;

    private Long intentos;

    private Long puntos;



}
