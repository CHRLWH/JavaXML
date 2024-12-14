package Logica;

import Datos.CRUD;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

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

    public static void cuadroDeEstadisticas(Node node, int nivel) {
        // Mostrar el nodo actual con su nivel (jerarquía)

        System.out.println("Nivel " + nivel + ": " + node.getNodeName());

        // Recorrer los nodos hijos
        NodeList nodosHijo = node.getChildNodes();
        Node child;
        int conteoDeHijos = 0;

        for (int i = 0; i < nodosHijo.getLength(); i++) {
            child = nodosHijo.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                conteoDeHijos++;
                // Llamada recursiva para los hijos
                cuadroDeEstadisticas(child, nivel + 1);
            }
        }

        // Mostrar el conteo de hijos por nivel
        if (conteoDeHijos > 0) {
            System.out.println("Número de nodos hijos en nivel " + nivel + ": " + conteoDeHijos);
        }
    }
}

