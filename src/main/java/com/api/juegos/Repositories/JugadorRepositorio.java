package com.api.juegos.Repositories;

import com.api.juegos.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepositorio extends JpaRepository<Jugador,Long> {
}
