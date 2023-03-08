# api-juegos
Api para guardar resultados de partidas en diversos juegos de palabras
Esta es la rama maestra que contiene el resultado final

## Cambios realizados respecto a la rama anterior

**BASE DE DATOS**

Añadido delete set null al equipo de los jugadores cuando se borra un equipo

Borrada tabla de clasificaciones ya que al final van por dtos y funciones JPA

Nuevos datos

**CÓDIGO**

- Añadido Crud de palabra completo con manejo de un fichero txt

| Endpoint                                  | Resultado                                                               | Método   |
|------------------------------------------ |-------------------------------------------------------------------------|:--------:|
|`/palabras`                                | Muestra todas las palabras                                              | GET      |
|`/palabra/{cantidad}`                      | Muestra un numero de palabras aleatorias                                | GET      |
|`/palabra/{cantidad}/comienza/{cadena}`    | Muestra un numero de palabras aleatorias que comienzan por una cadena   | GET      |
|`/palabra/{cantidad}/termina/{cadena}`     | Muestra un numero de palabras aleatorias que terminan por una cadena    | GET      |
|`/palabra/{cantidad}/contiene/{cadena}`    | Muestra un numero de palabras aleatorias que contienen una cadena       | GET      |

- Arreglada la gestión de errores, ya no muestran un campo trace kilométrico

- Mejorados los endpoints del método POST de los diversos CRUD, ahora son en singular, lo que tiene mas sentido

- Ahora el POST de partida realiza un calculo de puntos **(Longitud de palabra * 10)/intentos realizados** y saca la palabra a jugar del fichero

- Añadida relación de equipo OneToMany con jugador, de manera que ahora se puede obtener la lista de jugadores en un equipo en el siguiente endpoint:

| Endpoint                   | Resultado                               | Método   |
|----------------------------|-----------------------------------------|:--------:|
|`/equipo/{id}/miembros`     | Muestra todos los miembros de un equipo | GET      |

- Añadidas clasificaciones por Jugador y Equipo en los siguientes endpoints:

| Endpoint                   | Resultado                               | Método   |
|----------------------------|-----------------------------------------|:--------:|
|`/equipos/clasificacion`    | Muestra la clasificacion por equipos    | GET      |
|`/jugadores/clasificacion`  | Muestra la clasificacion por jugadores  | GET      |

