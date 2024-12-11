package Datos;
import Logica.Articulo;
import Logica.Articulos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class CRUD {

    private final String rutaDelArchivoXml;

    public CRUD(String rutaDelArchivoXml) {

        this.rutaDelArchivoXml = rutaDelArchivoXml;

    }


    public static Document conectarConDocumento(String filePath) throws Exception{
        File file;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document doc;

            file = new File("src/Repositorio/articulos.xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            //Sin éste parse no podría partir de un nodo concreto
            doc = builder.parse(filePath);

        return doc;
    }
    public static ArrayList<Articulo> leerTodos(String primerNodoDesdeElQueLeer){

        //Zona declarativa

        ArrayList <Articulo> articulosAux = new ArrayList<Articulo>();
        Articulo articuloAux;
        String nombre;

        Document doc;
        NodeList nodeList;
        Node node;
        Element elemento;
        int id;
        int precio;

        //Zona ejecutiva

        try{

            doc = conectarConDocumento("src/Repositorio/articulos.xml");

            nodeList = doc.getElementsByTagName(primerNodoDesdeElQueLeer);
            for (int i = 0; i<nodeList.getLength();i++){
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    elemento = (Element) node;
                    id = Integer.parseInt(elemento.getAttribute("id"));
                    nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    precio = Integer.parseInt(elemento.getElementsByTagName("precio").item(0).getTextContent());;
                    articuloAux = new Articulo(id,nombre,precio);
                    articulosAux.add(articuloAux);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articulosAux;
    }
   public void agregar(Articulo entrenamiento) {

        //Zona declarativa

       Document doc;
       Element root;
       Element nuevoEntrenamiento;
       Element nombre;
       Element duracion;
       TransformerFactory transformerFactory;
       Transformer transformer;
       DOMSource source;
       StreamResult result;
       File file;
       //Zona ejecutiva
        try {

            doc = conectarConDocumento("src/Repositorio/articulos.xml");
            root = doc.getDocumentElement();

        // Crear un nuevo elemento "articulo"
            nuevoEntrenamiento = doc.createElement("articulos");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getPrecio()));


            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);


            root.appendChild(nuevoEntrenamiento);

            // Guardar los cambios en el archivo XML
            file = new File("src/Repositorio/articulos.xml");
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(file);
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar(int id, Articulo nuevoArticulo) {
        //Zona declarativa
        File file;
        Document doc;
        NodeList nodeList;
        Node node;
        Element elemento;
        TransformerFactory transformerFactory;
        Transformer transformer;
        DOMSource source;
        StreamResult result;
        //Zona ejecutiva
        try {

            doc = conectarConDocumento("src/Repositorio/articulos.xml");

            nodeList = doc.getElementsByTagName("articulo");

            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;

                    if (Integer.parseInt(elemento.getAttribute("id")) == id) {
                        elemento.getElementsByTagName("nombre").item(0).setTextContent(nuevoArticulo.getNombre());
                        elemento.getElementsByTagName("precio").item(0).setTextContent(String.valueOf(nuevoArticulo.getPrecio()));
                    }
                }
            }

// Guardar los cambios en el archivo XML
            file = new File("src/Repositorio/articulos.xml");
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deletear(int idDelArticuloABorrar){
        //Usar node.delete..

        try {
            File file = new File(rutaDelArchivoXml);
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
