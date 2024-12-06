package DAO;
import Service.Articulo;
import Service.Articulos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.attribute.Attribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public CRUD() {
    }



    public Articulos leer(String primerNodoListable){

        //Zona declarativa
        Articulos articulosAux = new Articulos();
        Articulo articuloAux;
        String nombre;
        File file;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document doc;
        NodeList nodeList;
        Node node;
        Element elemento;
        int id;
        int precio;

        //Zona ejecutiva
        try{
            file = new File("src/Repository/articulos.xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            //Sin éste parse no podría partir de un nodo concreto
            doc = builder.parse(file);

            nodeList = doc.getElementsByTagName(primerNodoListable);
            for (int i = 0; i<nodeList.getLength();i++){
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    elemento = (Element) node;
                    id = Integer.parseInt(elemento.getAttribute("id"));
                    nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    precio = Integer.parseInt(elemento.getElementsByTagName("precio").item(0).getTextContent());;
                    articuloAux = new Articulo(id,nombre,precio);
                    articulosAux.anhadirAlaLista(articuloAux);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articulosAux;
    }
   public void agregar(Articulo entrenamiento) {
        try {
            File file = new File("src/Repository/articulos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

        // Crear un nuevo elemento "articulo"
            Element nuevoEntrenamiento = doc.createElement("articulo");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            Element duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getPrecio()));


            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);


            root.appendChild(nuevoEntrenamiento);

            // Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletear(int idDelArticuloABorrar){
        //Usar node.delete..

        try {
            File file = new File("src/Repository/articulos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("articulo");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    if (Integer.parseInt(elemento.getAttribute("id")) == idDelArticuloABorrar) {
                        elemento.getParentNode().removeChild(elemento);
                        break;
                    }
                }
            }

// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
