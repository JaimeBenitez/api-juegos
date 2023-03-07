package com.api.juegos.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 43874491117560211L;


    public PartidaNotFoundException(Long id){
        super("No se puede encontrar la partida con la ID: " + id);
    }
}
