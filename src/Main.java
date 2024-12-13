import Datos.CRUD;
import Logica.Funcionalidades;

public class Main {
    public static void main(String[] args) {

        Funcionalidades.cuadroEstadisticas();
        CRUD.leerTodos("articulos");

    }
}