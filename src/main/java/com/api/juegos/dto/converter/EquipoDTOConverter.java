package com.api.juegos.dto.converter;

import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Jugador;
import com.api.juegos.dto.EquipoDTO;
import com.api.juegos.dto.JugadorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipoDTOConverter {

    private final ModelMapper modelMapper;

    public EquipoDTO convertToDTO (Equipo equipo){
        return modelMapper.map(equipo, EquipoDTO.class);
    }
}
