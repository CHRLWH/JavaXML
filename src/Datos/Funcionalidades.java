package Datos;

import Logica.Articulo;
import Logica.Articulos;
import Datos.CRUD;

import java.util.ArrayList;

public class Funcionalidades {

    public static Articulo compararPreciosDeArticulosPorId(int idArticulo1,int idArticulo2){

        //Comparador de precios buscando por id
        Articulos articulos = new Articulos();
        ArrayList <Articulo> articulosAux = new ArrayList<Articulo>();
        CRUD crud = new CRUD("src/Repositorio/articulos.xml");
        for (Articulo i:articulos.getArticulos()){
            if (i.getId() == idArticulo1 || i.getId() == idArticulo2){
                articulosAux.add(i);
            }
       }

        System.out.println("Articulos a comparar:");
        System.out.println("Primer articulo --> "+articulosAux.get(0).toString());
        System.out.println("Segundo articulo --> "+articulosAux.get(1).toString());
        if (articulosAux.get(0).getPrecio() > articulosAux.get(1).getPrecio()){
            System.out.println("El articulo --> "+articulosAux.get(0)+" es mayor");
            return articulosAux.get(0);
        }else{
            System.out.println("El articulo --> "+articulosAux.get(1)+ " es mayor");
            return articulosAux.get(1);
        }
    }
}
