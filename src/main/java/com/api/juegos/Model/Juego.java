package com.api.juegos.Model;

import com.api.juegos.Enum.Dificultad;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Partida> partidas;

}
