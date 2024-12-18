package Logica;

import Datos.CRUD;
import Presentacion.EntradaDeDatos;
import java.util.ArrayList;



public class Funcionalidades {

    public static Articulo compararPreciosDeArticulosPorId(int idArticulo1,int idArticulo2) {

        //Comparador de precios buscando por id

        //Zona declarativa
        Articulos articulos = new Articulos();
        ArrayList <Articulo> articulosAux = new ArrayList<Articulo>();
        //Zona ejecutiva
        for (Articulo i:articulos.getArticulos()){
            if (i.getId() == idArticulo1 || i.getId() == idArticulo2){
                articulosAux.add(i);
            }
       }

        System.out.println("Articulos a comparar:");
        System.out.println("PRIMER ARTICULO\n"+articulosAux.get(0).toString());
        System.out.println("SEGUNDO ARTICULO\n"+articulosAux.get(1).toString());
        if (articulosAux.get(0).getPrecio() > articulosAux.get(1).getPrecio()){
            System.out.println("ARTICULO MAS CARO \n"+articulosAux.get(0));
            return articulosAux.get(0);
        }else{
            System.out.println("ARTICULO MAS CARO \n"+articulosAux.get(1));
            return articulosAux.get(1);
        }
    }

    public static void cuadroEstadisticas(){

        Articulos articulos;
        int nodosPadre;
        int nodosHijo = 0;
        try {
                articulos = new Articulos();
                nodosPadre = articulos.contadorDeArticulos();
                System.out.println("Nodos padre --> "+nodosPadre);
                for (Articulo i : articulos.getArticulos()){
                    nodosHijo++;
                    System.out.println("   |_ Nodo"+ nodosHijo+ "--> "+Integer.toString(i.toString().length()).length()+" sub nodos.");

                }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void agregarDatos(){
        String nombre;
        int precio;
        Articulo articulo1;
        Articulos articulos;
        CRUD crud = new CRUD();

        do {
            nombre = EntradaDeDatos.pedirStringConMensaje("Introduce el nombre del articulo a añadir");
            precio = EntradaDeDatos.pedirNumeros("Introduce el precio de éste artículo");
            articulos = new Articulos();
            articulo1 = new Articulo(articulos.contadorDeArticulos()+1,nombre,precio);
        }while (!Character.isDigit(precio) || nombre.isEmpty());
        crud.agregar(articulo1);

    }

    public static void borrar(){
        System.out.println("Catalogo de articulos:");
        Principal.mostrarTodosLosDatos();
        int id = EntradaDeDatos.pedirNumeros("Introduce el id del articulo a borrar");
        CRUD fichero = new CRUD();
        fichero.deletear(id);
        System.out.println("Articulo borrado");
        System.out.println("Nueva lista de datos");
        Principal.mostrarTodosLosDatos();

    }

}
