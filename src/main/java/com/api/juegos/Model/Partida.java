package com.api.juegos.Model;

import com.api.juegos.Enum.Dificultad;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data @AllArgsConstructor @NoArgsConstructor
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
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_jugador")
    private Jugador jugador;

    @JsonBackReference
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_juego")
    private Juego juego;

    @Enumerated(EnumType.STRING)
    @Column(name="Dificultad")
    private Dificultad dificultad;


}
