# api-juegos
Api para guardar resultados de partidas en diversos juegos de palabras
Esta rama llega hasta terminar el CRUD de Partida

## Cambios realizados

**BASE DE DATOS**

Añadido delete cascade en partida cuando se borra un jugador o juego

Añadida id como PK de Partida

Nuevos datos

**CÓDIGO**

Añadido Crud de partida completo

| Endpoint                 | Resultado                                | Método   |
|------------------------- |------------------------------------------|:--------:|
|`/partidas`               | Muestra todas las partidas               | GET      |
|`/partidas/jugador/{id}`  | Muestra todas las partidas de un jugador | GET      |
|`/partidas/juego/{id}`    | Muestra todas las partidas de un juego   | GET      |
|`/partida/{id}`           | Muestra una partida por su id            | GET      |
|`/partidas`               | Crea una nueva partida                   | POST     |
|`/partida/{id}`           | Modifica una partida                     | PUT      |
|`/partida/{id}`           | Borra una partida                        | DELETE   |


