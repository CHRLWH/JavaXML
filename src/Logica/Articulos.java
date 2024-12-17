package Logica;

import Datos.CRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Articulos {
   private ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public Articulos() {
        CRUD crud = new CRUD();
        this.articulos = crud.leerTodos("articulo");
    }

    public Articulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }




    public int contadorDeArticulos(){
        return articulos.size();
    }
    public List<Articulo> getArticulos() {
        return articulos;
    }
}
