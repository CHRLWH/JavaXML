package Datos;

import Logica.Articulo;
import Logica.Articulos;
import Datos.CRUD;
public class Funcionalidades {

    public static double compararPreciosDeArticulosPorId(int idArticulo1,int idArticulo2){
        Articulos articulos = new Articulos();
        CRUD crud = new CRUD("src/Repositorio/articulos.xml");
        articulos = crud.leerTodos("articulo");
        for (Articulo i:articulos.ge){

        }
    }
}
