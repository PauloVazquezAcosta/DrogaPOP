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
                formato = "[a-zA-Z ]{2,254}";
                break;
            case "nombre":
                formato = "[A-Za-z]{2,254}";
                break;
            case "correo electronico":
                formato = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
                break;
            case "puesto de trabajo":
                formato = "[A-Za-z]{2,254}";
                break;
        }

        do {
            System.out.print("Introduzca " + tipoDato + " :");
            dato = entrada.nextLine();
            Pattern pattern = Pattern.compile(formato);
            matcher = pattern.matcher(dato);

        } while (!matcher.matches());

        return dato;
    }

}
