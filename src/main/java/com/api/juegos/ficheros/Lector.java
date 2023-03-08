package com.api.juegos.ficheros;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lector {

    public List<Palabra> leer(String archivo) throws Exception{
        List<Palabra> palabras = new ArrayList<>();
        //Para poder utilizar recursos de resources es necesario añadir esta linea
        File file = ResourceUtils.getFile("classpath:" + archivo);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String linea;
        while ((linea = reader.readLine()) != null){
            Palabra palabra  = new Palabra(linea.trim());
            palabras.add(palabra);
        }
        reader.close();
        return palabras;
    }



}
