package drogapop.entity.clasesAuxiliares;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Letras {
    /**
     * Veririca que el usuario introduzca una cadena válida.
     *
     * @param entrada traspasa el Scanner asociado al teclado.
     * @return String validado
     * @version 0.0.1
     */
    public static String setDatos(@org.jetbrains.annotations.NotNull Scanner entrada, String tipoDato) {
        String dato;
        Matcher matcher;
        String formato = "";
        switch (tipoDato) {
            case "apellidos":
                formato = "[A-Z]{2,254}" + " " + "[A-Z]{2,254}";
                break;
            case "nombre":
                formato = "[A-Z]{2,254}";
                break;
            case "correo electronico":
                formato = "[A-Z]{2,254}" + "@" + "[A-Z]{2,50}" + "." + "[A-Z]{2,6}";
                break;
            case "puesto de trabajo":
                formato = "[A-Z]{2,254}";
                break;
        }

        do {
            System.out.print("Introduza " + tipoDato + " :");
            dato = entrada.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile(formato);
            matcher = pattern.matcher(dato);

        } while (!matcher.matches());

        return dato;
    }

}
