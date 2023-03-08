package com.api.juegos.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Entity

public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String logo;

    private Long puntos = 0L;

    @JsonManagedReference
    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores = new ArrayList<>();
}
