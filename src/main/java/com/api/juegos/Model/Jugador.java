package com.api.juegos.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_equipo")
    private Equipo equipo = null;

    private Long puntos = 0L;

    private Boolean admin;

    private String clave;


    @JsonManagedReference
    @OneToMany(mappedBy = "jugador")
    private Set<Partida> partidas;
}
