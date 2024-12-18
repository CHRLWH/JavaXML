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



    public int getIdArticulo(int id){
        int idQueRetornar = 0;
        for (Articulo i: articulos){
            if (i.getId() == id){
                idQueRetornar = i.getId();
            }
        }
        return idQueRetornar;
    }
    public int contadorDeArticulos(){
        return articulos.size();
    }
    public List<Articulo> getArticulos() {
        return articulos;
    }
}
