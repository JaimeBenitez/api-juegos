package com.api.juegos.Controller;

import com.api.juegos.Errors.JugadorNotFoundException;
import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Jugador;
import com.api.juegos.Repositories.EquipoRepositorio;
import com.api.juegos.Repositories.JugadorRepositorio;

import com.api.juegos.dto.*;
import com.api.juegos.dto.converter.JugadorClasificacionDTOConverter;
import com.api.juegos.dto.converter.JugadorDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepositorio jugadorRepositorio;
    private final JugadorDTOConverter jugadorDTOConverter;

    private final EquipoRepositorio equipoRepositorio;

    private final JugadorClasificacionDTOConverter jugadorClasificacionDTOConverter;


    /**
     * Obtenemos todos los jugadores
     *
     * @return lista de jugadores
     */
    @GetMapping("/jugadores")
    public ResponseEntity<List<?>> getAllPlayers(){
        List<Jugador> players = jugadorRepositorio.findAll();
        if(players.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<JugadorDTO> dtoList =
                    players.stream().map(jugadorDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtenemos un jugador en base a su ID
     *
     * @param id
     * @return Error 404 si no encuentra el jugador
     */
    @GetMapping("jugador/{id}")
    public Jugador getPlayerById(@PathVariable Long id){

        return jugadorRepositorio.findById(id)
                .orElseThrow(() -> new JugadorNotFoundException(id));
    }

    /**
     * Obtenemos la clasificacion de jugadores segun sus puntos
     *
     *
     * @return la lista de jugadores
     */
    @GetMapping("/jugadores/clasificacion")
    public ResponseEntity<List<?>> getTeamLeaderBoard(){
        List<Jugador> jugadores = jugadorRepositorio.findByOrderByPuntosDesc();

        if(jugadores.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            List<ClasificacionJugadorDTO> dtoList =
                    jugadores.stream().map(jugadorClasificacionDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }

    }
    /**
     * Creamos un nuevo jugador
     *
     * @param nuevo
     * @return jugador insertado
     */
    @PostMapping("/jugador")
    public ResponseEntity<?> newPlayer(@RequestBody CrearJugadorDTO nuevo){
        Jugador nJugador = new Jugador();
        Equipo equipo = null;
        //Para poder meter el repositorio completo necesitamos buscarlo usando la id que le pasamos en el DTO
        nJugador.setNombre(nuevo.getNombre());
        nJugador.setAvatar(nuevo.getAvatar());
        //Para poder considerar un equipo como nulo tenemos que comprobar antes de hacer el findById
        if(nuevo.getEquipoId() != null){
            equipo = equipoRepositorio.findById(nuevo.getEquipoId()).orElse(null);
        }
        nJugador.setEquipo(equipo);
        nJugador.setPuntos(nuevo.getPuntos());
        nJugador.setAdmin(nuevo.getAdmin());
        nJugador.setClave(nuevo.getClave());
        return ResponseEntity.status(HttpStatus.CREATED).body(jugadorRepositorio.save(nJugador));
    }

    /**
     * Actualizamos un jugador
     *
     * @param nuevo
     * @param id
     * @return jugador actualizado, 404 si no se encuentra el jugador
     */
    @PutMapping("/jugador/{id}")
    public Jugador updatePlayer(@RequestBody ModJugadorDTO nuevo, @PathVariable Long id) {
        Equipo equipo = null;
        if(nuevo.getEquipoId() != null){
            equipo = equipoRepositorio.findById(nuevo.getEquipoId()).orElse(null);
        }
        //Para que lo admita la lambda la variable debe ser final, asi que todas las comprobaciones las debemos hacer en
        //otra variable
        final Equipo nEquipo = equipo;
        return jugadorRepositorio.findById(id).map(j -> {
            j.setNombre(nuevo.getNombre());
            j.setAvatar(nuevo.getAvatar());
            j.setEquipo(nEquipo);
            j.setPuntos(nuevo.getPuntos());
            j.setAdmin(nuevo.getAdmin());
            j.setClave(nuevo.getClave());
            return jugadorRepositorio.save(j);
        }).orElseThrow(() -> new JugadorNotFoundException(id));
    }

    /**
     * Eliminamos un jugador en base a su ID
     *
     * @param id
     * @return C??digo 204 sin contenido
     */
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id){
        Jugador jugador = jugadorRepositorio.findById(id).orElseThrow(() -> new JugadorNotFoundException(id));
        jugadorRepositorio.delete(jugador);
        return ResponseEntity.noContent().build();
    }
}
