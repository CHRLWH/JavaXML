package Logica;

import Datos.CRUD;
import Presentacion.EntradaDeDatos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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

    public static void cuadroEstadisticas(){

        Articulos articulos;
        int nodosPadre;
        int nodosHijo = 0;
        try {
                articulos = new Articulos();
                nodosPadre = articulos.contadorDeArticulos();

                for (Articulo i : articulos.getArticulos()){
                    System.out.println(i.toString().length());

                    nodosHijo++;
                }


            System.out.println("Nodos padre --> "+nodosPadre);
            System.out.println("Nodos hijo --> "+nodosHijo);
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

        nombre = EntradaDeDatos.pedirStringConMensaje("Introduce el nombre del articulo a añadir");
        precio = EntradaDeDatos.pedirNumeros("Introduce el precio de éste artículo");
        articulos = new Articulos();
        articulo1 = new Articulo(articulos.contadorDeArticulos()+1,nombre,precio);

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
