package com.api.juegos.Controller;

import com.api.juegos.Errors.PalabraNotFoundException;
import com.api.juegos.Servicios.PalabraServicio;
import com.api.juegos.ficheros.Palabra;
import lombok.AllArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@AllArgsConstructor
public class PalabraController {


   private PalabraServicio palabraServicio;

   /**
    * Obtenemos todas las palabras
    *
    * @return lista de palabras
    */
   @GetMapping("/palabras")
    public List<String> getAllWords() throws Exception{

       return palabraServicio.cargarLista();
   }

   /**
    * Obtenemos una lista de la cantidad que le pasemos de palabras
    *
    * @param cantidad Cantidad de palabras a devolver
    * @return lista de palabras
    */
   @GetMapping("/palabra/{cantidad}")
   public List<String> getWords(@PathVariable Long cantidad) throws Exception{
      List<String> palabras = palabraServicio.cargarLista();
      return palabraServicio.palabrasRandom(palabras,cantidad);

   }
   /**
    * Obtenemos una lista de la cantidad que le pasemos de palabras que empiecen por una cadena concreta
    *
    * @param cantidad Cantidad de palabras a devolver
    * @param cadena Cadena de busqueda
    * @return lista de palabras
    */
   @GetMapping("palabra/{cantidad}/comienza/{cadena}")
   public List<String> getWordsStartsWith(@PathVariable Long cantidad, @PathVariable String cadena) throws Exception{
      List<String> palabras = palabraServicio.cargarLista();
      List<String> palabrasFiltradas = palabraServicio.palabrasComienzaPor(palabras,cadena);
      //Tenemos que hacer el control de errores antes del random, ya que de lo contrario si le llega
      //una lista vacia dara un 500
      if(palabrasFiltradas.isEmpty()){
            throw new PalabraNotFoundException(cadena);
      }else{
         return palabraServicio.palabrasRandom(palabrasFiltradas,cantidad);
      }
   }

   /**
    * Obtenemos una lista de la cantidad que le pasemos de palabras que terminen por una cadena concreta
    *
    * @param cantidad Cantidad de palabras a devolver
    * @param cadena Cadena de busqueda
    * @return lista de palabras
    */
   @GetMapping("palabra/{cantidad}/termina/{cadena}")
   public List<String> getWordsEndsWith(@PathVariable Long cantidad, @PathVariable String cadena) throws Exception{
      List<String> palabras = palabraServicio.cargarLista();
      List<String> palabrasFiltradas = palabraServicio.palabrasTerminaPor(palabras,cadena);
      //Tenemos que hacer el control de errores antes del random, ya que de lo contrario si le llega
      //una lista vacia dara un 500
      if(palabrasFiltradas.isEmpty()){
         throw new PalabraNotFoundException(cadena);
      }else{
         return palabraServicio.palabrasRandom(palabrasFiltradas,cantidad);
      }
   }

   /**
    * Obtenemos una lista de la cantidad que le pasemos de palabras que contengan una cadena concreta
    *
    * @param cantidad Cantidad de palabras a devolver
    * @param cadena Cadena de busqueda
    * @return lista de palabras
    */
   @GetMapping("palabra/{cantidad}/contiene/{cadena}")
   public List<String> getWordsContains(@PathVariable Long cantidad, @PathVariable String cadena) throws Exception{
      List<String> palabras = palabraServicio.cargarLista();
      List<String> palabrasFiltradas = palabraServicio.palabrasContiene(palabras,cadena);
      //Tenemos que hacer el control de errores antes del random, ya que de lo contrario si le llega
      //una lista vacia dara un 500
      if(palabrasFiltradas.isEmpty()){
         throw new PalabraNotFoundException(cadena);
      }else{
         return palabraServicio.palabrasRandom(palabrasFiltradas,cantidad);
      }
   }
}
