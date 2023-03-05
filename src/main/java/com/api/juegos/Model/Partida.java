package com.api.juegos.Model;

import com.api.juegos.Enum.Dificultad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Partida {

    private String palabra;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha_hora;

    private Long intentos;

    private Long puntos;

    private Long id_jugador;

    private Long id_juego;

    @Enumerated(EnumType.STRING)
    @Column(name="Dificultad")
    private Dificultad dificultad;


}
