package com.api.juegos.Servicios;

import com.api.juegos.ficheros.Lector;
import com.api.juegos.ficheros.Palabra;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PalabraServicio {

    public List<String> cargarLista() throws Exception{
        Lector lector = new Lector();
        List<Palabra> palabras = lector.leer("palabras.txt");
        List<String> resultado = new ArrayList<>();
        //Transformamos el objeto palabra en String para facilitar su uso
        for(int i = 0; i < palabras.size();i++){
            resultado.add(palabras.get(i).getValue());
        }
        return resultado;
    }

    public List<String> palabrasRandom(List<String> palabras, Long cantidad){
        Random rand = new Random();
        List<String> resultado = new ArrayList<>();
        for(int i = 0; i < cantidad;i++){
            int nAleatorio = rand.nextInt(palabras.size());
            String palabraAzar = palabras.get(nAleatorio);
            //De esta forma nos aseguramos que no se repitan palabras
            if(!resultado.contains(palabraAzar)) {
                resultado.add(palabraAzar);
            }
        }
        return resultado;

    }

    public List<String> palabrasComienzaPor(List<String> palabras, String comienzoPalabra){
        List<String> resultado = new ArrayList<>();

        for(int i = 0; i < palabras.size(); i++){
            if(palabras.get(i).startsWith(comienzoPalabra)){
                resultado.add(palabras.get(i));
            }
        }
        return resultado;
    }

    public List<String> palabrasTerminaPor(List<String> palabras, String finalPalabra){
        List<String> resultado = new ArrayList<>();

        for(int i = 0; i < palabras.size(); i++){
            if(palabras.get(i).endsWith(finalPalabra)){
                resultado.add(palabras.get(i));
            }
        }
        return resultado;
    }

    public List<String> palabrasContiene(List<String> palabras, String cadena){
        List<String> resultado = new ArrayList<>();

        for(int i = 0; i < palabras.size(); i++){
            if(palabras.get(i).contains(cadena)){
                resultado.add(palabras.get(i));
            }
        }
        return resultado;
    }
}
