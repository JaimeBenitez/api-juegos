package com.api.juegos.Repositories;

import com.api.juegos.Model.Equipo;
import com.api.juegos.dto.ClasificacionEquipoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepositorio extends JpaRepository<Equipo,Long> {

        List<Equipo> findByOrderByPuntosDesc();
}
