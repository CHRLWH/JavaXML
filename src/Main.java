import Datos.CRUD;
import Logica.Funcionalidades;
import org.w3c.dom.Document;

public class Main {
    public static void main(String[] args) throws Exception {
        Document documento = CRUD.conectarConDocumento("src/Repositorio/articulos.xml");
        Funcionalidades.cuadroDeEstadisticas(documento.getDocumentElement(),0);


    }
}