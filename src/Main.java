import Datos.CRUD;
import Logica.Articulo;
import Logica.Funcionalidades;
import org.w3c.dom.Document;

public class Main {
    public static void main(String[] args) throws Exception {
        Articulo articulo = new Articulo(5,"lapiz",10);
        CRUD ruta = new CRUD("src/Repositorio/articulos.xml");
        ruta.agregar(articulo);


    }
}