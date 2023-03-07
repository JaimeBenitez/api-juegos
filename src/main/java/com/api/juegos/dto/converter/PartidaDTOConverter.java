package com.api.juegos.dto.converter;


import com.api.juegos.Model.Partida;
import com.api.juegos.dto.PartidaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidaDTOConverter {

    private final ModelMapper modelMapper;

    public PartidaDTO convertToDTO (Partida partida){
        return modelMapper.map(partida, PartidaDTO.class);
    }


}
