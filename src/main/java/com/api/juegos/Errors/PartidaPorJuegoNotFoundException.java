package com.api.juegos.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaPorJuegoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 43874481117560211L;


    public PartidaPorJuegoNotFoundException(Long id){
        super("No se puede encontrar la partida del juego con la ID: " + id);
    }
}
