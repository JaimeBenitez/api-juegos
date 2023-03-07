package com.api.juegos.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JuegoNotFoundException extends RuntimeException {

    private static final  long serialVersionUID = 43838691117760211L;

    public JuegoNotFoundException (Long id){
        super("No se puede encontrar el juego con la ID: " + id);
    }
}
