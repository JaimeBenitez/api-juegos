package com.api.juegos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JugadorDTO {

    private Long id;

    private String nombre;

    private String avatar;

    private Long equipoId;

    private Long puntos;

    private Boolean admin;

    private String clave;

}
