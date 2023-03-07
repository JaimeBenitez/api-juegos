package com.api.juegos.Repositories;

import com.api.juegos.Model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepositorio extends JpaRepository<Juego,Long> {
}
