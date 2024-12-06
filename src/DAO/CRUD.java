package DAO;
import Service.Persona;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public CRUD() {
    }



    public List<Persona> leer(String primerNodoListable){
        //Zona declarativa
        List <Persona> personas = new ArrayList<>();
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
                    id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
                    nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    precio = Integer.parseInt(elemento.getElementsByTagName("precio").item(0).getTextContent());;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personas;
    }
}
