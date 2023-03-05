package com.api.juegos.Repositories;

import com.api.juegos.Model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepositorio extends JpaRepository<Partida,Long> {
}
