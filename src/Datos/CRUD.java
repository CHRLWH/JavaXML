package Datos;
import Logica.Articulo;
import Logica.Articulos;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class CRUD {

    private final String rutaDelArchivoXml;

    public CRUD(String rutaDelArchivoXml) {

        this.rutaDelArchivoXml = rutaDelArchivoXml;

    }

    public static Document conectarConDocumento(String filePath) {
        File file;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document doc = null;
    try {


        file = new File("src/Repositorio/articulos.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        //Sin éste parse no podría partir de un nodo concreto
        doc = builder.parse(filePath);
    }catch (ParserConfigurationException e){
        System.out.println("Fallo de parseo");

    }catch (Exception a){
        System.out.println("Error desconocido");
        a.printStackTrace();
    }
        return doc;

    }
    public static ArrayList<Articulo> leerTodos(String primerNodoDesdeElQueLeer) throws NumberFormatException{

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
        } catch (DOMException e) {
            System.out.println("Problema con valor de nodo");
            e.printStackTrace();
        }catch (NullPointerException a){
            System.out.println("Valor null detectado");
        }catch (Exception i){
            System.out.println("Excepcion desconocida");
            i.printStackTrace();
        }finally {
            //Cerrar las conexiones pero no se que tengo que cerrar exactamente
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
            nuevoEntrenamiento = doc.createElement("articulo");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            duracion = doc.createElement("precio");
            duracion.setTextContent(String.valueOf(entrenamiento.getPrecio()));

            nuevoEntrenamiento.appendChild(duracion);
            nuevoEntrenamiento.appendChild(nombre);

            root.appendChild(nuevoEntrenamiento);

            // Guardar los cambios en el archivo XML
            file = new File("src/Repositorio/articulos.xml");
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
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
