package Logica;

import Datos.CRUD;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import static Datos.CRUD.conectarConDocumento;

public class Funcionalidades {

    public static Articulo compararPreciosDeArticulosPorId(int idArticulo1,int idArticulo2) {

        //Comparador de precios buscando por id

        //Zona declarativa
        Articulos articulos = new Articulos();
        ArrayList <Articulo> articulosAux = new ArrayList<Articulo>();
        CRUD crud = new CRUD("src/Repositorio/articulos.xml");

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


        Document doc;
        NodeList nodeList;
        Node nodePadre;
        Node nodoHijo;
        Node nodoNieto;
        Element elemento;
        int contadorHijos = 0;
        int contadorNietos =0;


        //Zona ejecutiva

        try{
            doc = conectarConDocumento("src/Repositorio/articulos.xml");

            nodeList = doc.getElementsByTagName("articulos");

                nodoHijo = nodeList.item(1);
                nodePadre = nodoHijo.getParentNode();
                contadorHijos = nodePadre.getChildNodes().getLength();
                contadorNietos = nodoHijo.getChildNodes().getLength();

            System.out.println(contadorHijos);
            System.out.println(contadorNietos);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
