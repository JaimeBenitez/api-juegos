package com.api.juegos.Controller;

import com.api.juegos.Enum.Dificultad;
import com.api.juegos.Errors.*;
import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Juego;
import com.api.juegos.Model.Jugador;
import com.api.juegos.Model.Partida;
import com.api.juegos.Repositories.JuegoRepositorio;
import com.api.juegos.Repositories.JugadorRepositorio;
import com.api.juegos.Repositories.PartidaRepositorio;
import com.api.juegos.dto.*;
import com.api.juegos.dto.converter.PartidaDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaRepositorio partidaRepositorio;
    private final JugadorRepositorio jugadorRepositorio;
    private final JuegoRepositorio juegoRepositorio;

    private final PartidaDTOConverter partidaDTOConverter;


    /**
     * Obtenemos todas las partidas
     *
     * @return lista de partidas
     */
    @GetMapping("/partidas")
    public ResponseEntity<List<?>> getAllPlays(){
        List<Partida> plays = partidaRepositorio.findAll();
        if(plays.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<PartidaDTO> dtoList =
                    plays.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }

    }

    /**
     * Obtenemos una lista de partidas ordenadas por los puntos obtenidos en base a la ID del jugador
     *
     * @param id
     * @return Error 404 si no encuentra el jugador
     */
    @GetMapping("partidas/jugador/{id}")
    public ResponseEntity<List<?>>getPlayByPlayerId(@PathVariable Long id){
        List<Partida> partidas = partidaRepositorio.findAllByJugador_IdOrderByPuntosDesc(id);

        if(partidas.isEmpty()){
            throw new PartidaPorJugadorNotFoundException(id);
        }else {
            List<PartidaDTO> dtoList =
                    partidas.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtenemos una lista de partidas ordenadas por los puntos obtenidos en base a la ID del juego
     *
     * @param id
     * @return Error 404 si no encuentra el juego
     */
    @GetMapping("partidas/juego/{id}")
    public ResponseEntity<List<?>>getPlayByGameId(@PathVariable Long id){
        List<Partida> partidas = partidaRepositorio.findAllByJuego_IdOrderByPuntosDesc(id);

        if(partidas.isEmpty()){
            throw new PartidaPorJuegoNotFoundException(id);
        }else {
            List<PartidaDTO> dtoList =
                    partidas.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }
    /**
     * Obtenemos una  partidas concreta con todos los datos desplegados usando la id de la partida
     *
     * @param id
     * @return Error 404 si no encuentra el juego
     */
    @GetMapping("partida/{id}")
    public Partida getPlayById(@PathVariable Long id){
        return partidaRepositorio.findById(id)
                .orElseThrow(() -> new PartidaNotFoundException(id));
        }

    /**
     * Creamos una nueva partida
     *
     * @param nuevo
     * @return jugador insertado
     */
    @PostMapping("/partidas")
    public ResponseEntity<?> newPlay(@RequestBody CrearPartidaDTO nuevo){

        Jugador jugador = jugadorRepositorio.findById(nuevo.getJugadorId()).orElseThrow(()-> new JugadorNotFoundException(nuevo.getJugadorId()));
        Juego juego = juegoRepositorio.findById(nuevo.getJuegoId()).orElseThrow(()-> new JuegoNotFoundException(nuevo.getJuegoId()));
        Partida nPartida = new Partida();
        nPartida.setPalabra(nuevo.getPalabra());
        nPartida.setFecha_hora(LocalDateTime.now());
        nPartida.setIntentos(nuevo.getIntentos());
        nPartida.setPuntos(nuevo.getPuntos());
        nPartida.setJugador(jugador);
        nPartida.setJuego(juego);
        nPartida.setDificultad(Dificultad.valueOf(nuevo.getDificultad()));

        return ResponseEntity.status(HttpStatus.CREATED).body(partidaRepositorio.save(nPartida));
    }

    /**
     * Actualizamos una partida
     *
     * @param nuevo
     * @param id
     * @return partida actualizada, 404 si no se encuentra la partida
     */
    @PutMapping("/partida/{id}")
    public Partida updatePlay(@RequestBody CrearPartidaDTO nuevo, @PathVariable Long id) {
       final Jugador jugador = jugadorRepositorio.findById(nuevo.getJugadorId()).orElseThrow(()-> new JugadorNotFoundException(nuevo.getJugadorId()));
       final Juego juego = juegoRepositorio.findById(nuevo.getJuegoId()).orElseThrow(()-> new JuegoNotFoundException(nuevo.getJuegoId()));

        //No modificamos la fecha porque en el caso de una partida realizada, tiene sentido que puedan cambiar los datos de la
        //partida pero no la fecha en la que se realizó
        return partidaRepositorio.findById(id).map(p -> {
            p.setPalabra(nuevo.getPalabra());
            p.setIntentos(nuevo.getIntentos());
            p.setPuntos(nuevo.getPuntos());
            p.setJugador(jugador);
            p.setJuego(juego);
            p.setDificultad(Dificultad.valueOf(nuevo.getDificultad()));
            return partidaRepositorio.save(p);
        }).orElseThrow(() -> new PartidaNotFoundException(id));
    }

    /**
     * Eliminamos una partida en base a su ID
     *
     * @param id
     * @return Código 204 sin contenido
     */
    @DeleteMapping("/partida/{id}")
    public ResponseEntity<Void> deletePlayById(@PathVariable Long id){
        Partida partida = partidaRepositorio.findById(id).orElseThrow(() -> new PartidaNotFoundException(id));
        partidaRepositorio.delete(partida);
        return ResponseEntity.noContent().build();
    }

}
