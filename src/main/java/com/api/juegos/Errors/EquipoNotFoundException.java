package com.api.juegos.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 43876691117560211L;

    public EquipoNotFoundException (Long id){
        super("No se puede encontrar el equipo con la ID: " + id);
    }
}
