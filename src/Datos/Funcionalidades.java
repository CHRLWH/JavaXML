package Datos;

import Logica.Articulo;
import Logica.Articulos;
import Datos.CRUD;
public class Funcionalidades {

    public static double compararPreciosDeArticulosPorId(int idArticulo1,int idArticulo2){
        Articulos articulos = new Articulos();
        CRUD crud = new CRUD("src/Repositorio/articulos.xml");
        for (Articulo i:articulos.getArticulos()){

       }
    }
}
