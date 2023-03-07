# api-juegos
Api para guardar resultados de partidas en diversos juegos de palabras
Esta rama llega hasta terminar los CRUD de Jugador y equipo

##Cambios realizados

**BASE DE DATOS**

Añadidos datos de prueba

**CÓDIGO**

Añadido Crud de jugador completo

| Endpoint                 | Resultado                                | Método   |
|------------------------- |------------------------------------------|:--------:|
|`/jugadores`              | Muestra todos los jugadores              | GET      |
|`jugador/{id}`            | Muestra un jugador por su id             | GET      |
|`/jugadores`              | Crea un nuevo jugador                    | POST     |
|`/jugador/{id}`           | Modifica un jugador                      | PUT      |
|`/jugador/{id}`           | Borra un jugador                         | DELETE   |

Añadido Crud de equipo completo

| Endpoint                 | Resultado                                | Método   |
|------------------------- |------------------------------------------|:--------:|
|`/equipos`                | Muestra todos los equipos                | GET      |
|`/equipo/{id}`            | Muestra un equipo por su id              | GET      |
|`/equipos`                | Crea un nuevo equipo                     | POST     |
|`/equipo/{id}`            | Modifica un equipo                       | PUT      |
|`/equipo/{id}`            | Borra un equipo                          | DELETE   |

