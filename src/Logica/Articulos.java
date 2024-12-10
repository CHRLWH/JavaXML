package Logica;

import Datos.CRUD;

import java.util.ArrayList;
import java.util.List;

public class Articulos {
   private ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public Articulos() {
        this.articulos = CRUD.leerTodos();
    }

    public void anhadirAlaLista(Articulo articuloQueInsertar){
        articulos.add(articuloQueInsertar);
    }

    @Override
    public String toString() {
        return "Articulos{" +
                "articulos=" + articulos +
                '}';
    }

    public String getArticulo(String articuloAConseguir) {

        return String.valueOf(articulos.stream().
                filter(articulo1 -> articulo1.toString().
                        equals(articuloAConseguir)));
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }
}
