import DAO.CRUD;
import Service.Articulo;
import Service.Articulos;

public class Main {
    public static void main(String[] args) {
        CRUD xml = new CRUD();
        Articulo articulo = new Articulo(2,"medicinas",33);
        xml.actualizar(2,articulo);

    }
}