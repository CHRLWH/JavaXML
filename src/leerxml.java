import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class leerxml {

    public leerxml() {
    }

    public List<Persona> leer(String primerNodoListable){
        List <Persona> personas = new ArrayList<>();

        try{
            File file = new File("xml/articulos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Sin éste parse no podría partir de un nodo concreto
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName(primerNodoListable);
            for (int i = 0; i<nodeList.getLength();i++){
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) node;

                    String nombre = elemento.getElementsByTagName("nombre").toString();
                    int id = Integer.parseInt(elemento.getElementsByTagName("precio").toString());

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return personas;
    }
}
