package com.api.juegos.Model;

import com.api.juegos.Enum.Dificultad;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;


@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity

public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabra;


    private LocalDateTime fecha_hora;

    private Long intentos;

    private Long puntos;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_jugador")
    private Jugador jugador;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_juego")
    private Juego juego;

    @Enumerated(EnumType.STRING)
    @Column(name="Dificultad")
    private Dificultad dificultad;


}
