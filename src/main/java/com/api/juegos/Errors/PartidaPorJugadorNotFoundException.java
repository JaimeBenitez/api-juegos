package com.api.juegos.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaPorJugadorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 43874491117560211L;


    public PartidaPorJugadorNotFoundException (Long id){
        super("No se puede encontrar la partida del jugador con la ID: " + id);
    }
}
