package com.api.juegos.Repositories;

import com.api.juegos.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepositorio extends JpaRepository<Partida,Long> {

        List<Partida> findAllByJugador_IdOrderByPuntosDesc(Long id);

        List<Partida> findAllByJuego_IdOrderByPuntosDesc(Long id);


}
