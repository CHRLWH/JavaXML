package Presentacion;

import Logica.Articulos;
import Logica.Principal;

public class Menu {

    public static void iniciarPrograma() {
        String salida = "";
        String mensajeBienvenida = "Bienvenido! Escoge una opcion";
        do {

            System.out.println(mensajeBienvenida);
            int menu = EntradaDeDatos.pedirNumeros("1.-Mostrar todos los datos\n2.-Borrar\n3.-Añadir\n4.-Actualizar articulo\n5.-Comparar precios\n6.-Cuadro de estadisticas");
            switch (menu){
                case 1:
                    Principal.mostrarTodosLosDatos();
                    break;
                case 2:
                    Principal.deletearProducto();
                    break;
                case 3:
                    Principal.agregarProducto();
                    break;
                case 4:
                    Principal.cambiarProducto();
                    break;
                case 5:
                    Principal.comparadorDePrecios();
                    break;
                case 6:
                    Principal.cuadroDeStats();
                    break;
                default:
                    System.out.println("finished");
                   break;
            }
            mensajeBienvenida = "Bienvenido otra vez! Vuelve a escoger una opcion.";
            salida = EntradaDeDatos.pedirStringConMensaje("salir? S/N");
        }while (!salida.equalsIgnoreCase("s"));
    }
}
