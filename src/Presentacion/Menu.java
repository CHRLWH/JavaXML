package Presentacion;

import Logica.Articulos;
import Logica.Principal;

public class Menu {
    public Menu() {
    }

    public static void iniciarPrograma() {

        int menu = EntradaDeDatos.pedirNumeros("1.-Mostrar todos los datos\n2.-Borrar\n3.-Añadir\n4.-Comparar precios\n5.-Cuadro de estadisticas");
        switch (menu){
            case 1:
                    Principal.mostrarTodosLosDatos();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                Principal.comparadorDePrecios();
                break;
            case 5:
                Principal.cuadroDeStats();
                break;
        }
    }
}
