package Logica;

import Datos.CRUD;
import Presentacion.EntradaDeDatos;
import org.w3c.dom.*;

import java.util.InputMismatchException;

public class Principal {

    public static void mostrarTodosLosDatos(){
       try {
           Articulos articulos = new Articulos();
           articulos.getArticulos().stream().forEach(System.out::println);
       }catch (Exception e){
           System.out.println("Error desconocido");
           e.printStackTrace();
       }
    }

    public static void comparadorDePrecios() {

        int precio1 = 0;
        int precio2 = 0;
        boolean excepcionDisparada;
        String textoDeInicio = "Bienvenido al comparador de precios!";

        do {
            excepcionDisparada = false;
            try {
                System.out.println(textoDeInicio);
                precio1 = EntradaDeDatos.pedirNumeros("Introduce el id del primer articulo a comparar");
                precio2 = EntradaDeDatos.pedirNumeros("Ahora introduce el id del segundo precio a comparar");
                Funcionalidades.compararPreciosDeArticulosPorId(precio1, precio2);

            }catch (InputMismatchException i) {
                excepcionDisparada = true;
                System.out.println("Valor erróneo. Recuerda introducir el id del producto. El id de los productos esta representado en un numero de un solo digito.");
                textoDeInicio = "Vuelve a introducir los id!";
            } catch (IndexOutOfBoundsException o){
                excepcionDisparada = true;
                System.out.println("No existe ningun articulo con el id proporcionado");
                textoDeInicio = "Vuelve a introducir los id!";
            }
            catch (Exception e) {
                System.out.println("Error desconocida!");
                e.printStackTrace();
            }
        } while (excepcionDisparada);
    }

    public static void cuadroDeStats() {
            try {
                Funcionalidades.cuadroEstadisticas();
            }catch (Exception e){
                System.out.println("Error desconocida");
                e.printStackTrace();
            }
    }

    public static void agregarProducto() {
        try {
            Funcionalidades.agregarDatos();
        }catch (Exception a){
            System.out.println("Error desconocida");
            a.printStackTrace();
        }
    }

    public static void deletearProducto(){
        try {
            Funcionalidades.borrar();
        }catch (Exception a){
            System.out.println("Error desconocido");
            a.printStackTrace();
        }
    }
}
