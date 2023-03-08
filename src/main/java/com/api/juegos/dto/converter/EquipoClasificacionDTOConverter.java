package com.api.juegos.dto.converter;

import com.api.juegos.Model.Equipo;
import com.api.juegos.dto.ClasificacionEquipoDTO;
import com.api.juegos.dto.EquipoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipoClasificacionDTOConverter {

    private final ModelMapper modelMapper;

    public ClasificacionEquipoDTO convertToDTO (Equipo equipo){ return modelMapper.map(equipo, ClasificacionEquipoDTO.class);
    }
}
