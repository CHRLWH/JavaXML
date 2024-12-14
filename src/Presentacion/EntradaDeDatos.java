package Presentacion;

import java.util.Scanner;

/**
 * La clase {@code EntradaDeDatos} proporciona métodos estáticos para solicitar diferentes tipos de entradas del usuario,
 * como cadenas de texto, números enteros y decimales. Los métodos incluyen un mensaje opcional para guiar al usuario
 * sobre lo que debe ingresar.
 * @author Carlos Hernandez Herrador
 * @version 1.0
 * @since 2024
 */
public class EntradaDeDatos {


    public static String pedirStringConMensaje(String mensaje) {
        rotular(mensaje);
        String lector = new Scanner(System.in).nextLine();
        return lector;
    }


    public static String pedirStringSinMensaje() {
        String lector = new Scanner(System.in).nextLine();
        return lector;
    }


    public static int pedirNumeros(String mensaje) {
        rotular(mensaje);
        int lector = new Scanner(System.in).nextInt();
        return lector;
    }

    /**
     * Solicita al usuario que ingrese un número decimal, mostrando un mensaje previo.
     *
     * @param mensaje El mensaje que se muestra al usuario antes de solicitar la entrada.
     * @return El número decimal ingresado por el usuario.
     */
    public static double pedirConDecimales(String mensaje) {
        rotular(mensaje);
        double numeroConDecimales = new Scanner(System.in).nextInt();
        return numeroConDecimales;
    }

    /* ROTULOS CON MENSAJES */

    /**
     * Muestra un mensaje en color verde antes de solicitar una entrada del usuario.
     *
     * @param rotulo El mensaje que se muestra en color verde.
     */
    private static void rotular(String rotulo) {
        String resetear = "\u001B[0m";
        String verde = "\u001B[32m";
        System.out.println(verde + rotulo + resetear);
    }
}
