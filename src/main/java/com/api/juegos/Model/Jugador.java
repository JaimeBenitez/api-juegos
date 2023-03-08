package com.api.juegos.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;



@Data @NoArgsConstructor @AllArgsConstructor
@Builder @Entity

public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String avatar;

    //Entendemos que cuando se registra un nuevo jugador aun tiene que ver que equipos hay para entrar a pertenecer a uno,
    // por eso el valor por defecto es null
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_equipo")
    private Equipo equipo = null;

    private Long puntos = 0L;

    private Boolean admin;

    private String clave;


    // @JsonManagedReference
    //@OneToMany(mappedBy = "jugador")
    //private List<Partida> partidas = new ArrayList<>();
}
