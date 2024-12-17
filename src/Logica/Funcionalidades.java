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

        int contadorDePadres = 0;
        int contadorDeHijos = 0;
        try {
            //Create a DocumentBuilder
            File inputFile = new File("src/Repositorio/articulos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //Extract the root element
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("articulo");
            System.out.println("Node Length :" + nodeList.getLength());

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                System.out.println("\nCurrent Element :" + node.getNodeName());

                NodeList childNodes = node.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node childNode = childNodes.item(i);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        contadorDeHijos++;

                    }
                }

                System.out.println(contadorDeHijos);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void agregarDatos() {

        String nombre;
        int precio;
        Articulo articulo1;
        Articulos articulos;
        try{
            nombre = EntradaDeDatos.pedirStringConMensaje("Introduce el nombre del articulo a añadir");
            precio = EntradaDeDatos.pedirNumeros("Introduce el precio de éste artículo");
            articulos = new Articulos();
            articulo1 = new Articulo(articulos.contadorDeArticulos()+1,nombre,precio);


        }

    }

}
