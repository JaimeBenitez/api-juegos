package com.api.juegos.Controller;

import com.api.juegos.Errors.EquipoNotFoundException;
import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Jugador;
import com.api.juegos.Repositories.EquipoRepositorio;
import com.api.juegos.dto.ClasificacionEquipoDTO;
import com.api.juegos.dto.CrearEquipoDTO;
import com.api.juegos.dto.EquipoDTO;
import com.api.juegos.dto.converter.EquipoClasificacionDTOConverter;
import com.api.juegos.dto.converter.EquipoDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoRepositorio equipoRepositorio;

    private final EquipoDTOConverter equipoDTOConverter;

    private final EquipoClasificacionDTOConverter equipoClasificacionDTOConverter;



    /**
     * Obtenemos todos los equipos
     *
     * @return lista de equipos
     */
    @GetMapping("/equipos")
    public ResponseEntity<List<?>> getAllTeams(){
        List<Equipo> equipos = equipoRepositorio.findAll();

        if(equipos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            List<EquipoDTO> dtoList =
                    equipos.stream().map(equipoDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtenemos un equipo en base a su ID
     *
     * @param id
     * @return Error 404 si no encuentra el equipo
     */
    @GetMapping("/equipo/{id}")
    public Equipo getTeamById(@PathVariable Long id){
        return equipoRepositorio.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
    }

    /**
     * Obtenemos los miembros de un equipo usando su ID
     *
     * @param id
     * @return Error 404 si no encuentra el equipo
     */
    @GetMapping("/equipo/{id}/miembros")
    public List<Jugador> getTeamMembers(@PathVariable Long id){
        Equipo equipo = equipoRepositorio.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
        return equipo.getJugadores();
    }

    /**
     * Obtenemos la clasificacion de equipos segun sus puntos
     *
     *
     * @return la lista de equipos
     */
    @GetMapping("/equipos/clasificacion")
    public ResponseEntity<List<?>> getTeamLeaderBoard(){
        List<Equipo> equipos = equipoRepositorio.findByOrderByPuntosDesc();

        if(equipos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            List<ClasificacionEquipoDTO> dtoList =
                    equipos.stream().map(equipoClasificacionDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }

    }


    /**
     * Creamos un nuevo equipo
     *
     * @param nuevo
     * @return equipo insertado
     */
    @PostMapping("/equipo")
    public ResponseEntity<?> newTeam(@RequestBody CrearEquipoDTO nuevo){

            Equipo nEquipo = new Equipo();
            nEquipo.setNombre(nuevo.getNombre());
            nEquipo.setLogo(nuevo.getLogo());

            return ResponseEntity.status(HttpStatus.CREATED).body(equipoRepositorio.save(nEquipo));
    }

    /**
     * Actualizamos un equipo
     *
     * @param actualizado
     * @param id
     * @return cerveza actualizada, 404 si no se encuentra el equipo
     */
    @PutMapping("/equipo/{id}")
    public Equipo updateTeam(@RequestBody Equipo actualizado, @PathVariable Long id){
            return equipoRepositorio.findById(id).map(e ->{
                e.setNombre(actualizado.getNombre());
                e.setLogo(actualizado.getLogo());
                e.setPuntos(actualizado.getPuntos());
                return equipoRepositorio.save(e);
            }).orElseThrow(()-> new EquipoNotFoundException(id));
        }

    /**
     * Eliminamos un equipo en base a su ID
     *
     * @param id
     * @return Código 204 sin contenido
     */
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable Long id){
        Equipo equipo = equipoRepositorio.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
        equipoRepositorio.delete(equipo);
        return ResponseEntity.noContent().build();
    }

}
