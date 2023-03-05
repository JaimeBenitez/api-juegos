package com.api.juegos.dto.converter;

import com.api.juegos.Model.Jugador;
import com.api.juegos.dto.JugadorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JugadorDTOConverter {

    private final ModelMapper modelMapper;

    public JugadorDTO convertToDTO (Jugador jugador){
        return modelMapper.map(jugador, JugadorDTO.class);
    }
}
