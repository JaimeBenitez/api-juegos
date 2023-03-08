package com.api.juegos.Repositories;

import com.api.juegos.Model.Equipo;
import com.api.juegos.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepositorio extends JpaRepository<Jugador,Long> {

    List<Jugador> findByOrderByPuntosDesc();
}
