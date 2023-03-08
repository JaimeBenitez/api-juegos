package com.api.juegos.dto.converter;

import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Jugador;
import com.api.juegos.dto.ClasificacionEquipoDTO;
import com.api.juegos.dto.ClasificacionJugadorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JugadorClasificacionDTOConverter {

    private final ModelMapper modelMapper;

    public ClasificacionJugadorDTO convertToDTO (Jugador jugador){ return modelMapper.map(jugador, ClasificacionJugadorDTO.class);
    }
}
