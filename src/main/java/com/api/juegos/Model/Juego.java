package com.api.juegos.Model;

import com.api.juegos.Enum.Dificultad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Juego {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String instrucciones;

    @Enumerated(EnumType.STRING)
    @Column(name="Dificultad")
    private Dificultad dificultad;


    private Long max_intentos;

}
