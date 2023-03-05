package com.api.juegos.Controller;

import com.api.juegos.Errors.EquipoNotFoundException;
import com.api.juegos.Model.Equipo;
import com.api.juegos.Repositories.EquipoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoRepositorio equipoRepositorio;



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
            return ResponseEntity.ok(equipos);
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
     * Creamos un nuevo equipo
     *
     * @param nuevo
     * @return equipo insertado
     */
    @PostMapping("/equipos")
    public ResponseEntity<?> newTeam(@RequestBody Equipo nuevo){
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoRepositorio.save(nuevo));
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
