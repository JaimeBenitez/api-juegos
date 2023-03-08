package com.api.juegos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModPartidaDTO {



    private Long jugadorId;

    private Long juegoId;


    private String palabra;

    private String dificultad;

    private Long intentos;

    private Long puntos;




}
