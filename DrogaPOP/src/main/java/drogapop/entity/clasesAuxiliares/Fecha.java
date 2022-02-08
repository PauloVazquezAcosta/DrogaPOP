package drogapop.entity.clasesAuxiliares;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Fecha {
    /**
     * Solicita al usuario introducir una fecha y valida que esta este en el
     * formato deseado.
     *
     * @param input traspasa el Scanner asociado al teclado.
     * @return Date devuelve un objeto Date válido
     * @version 0.0.1
     */
    public static Date entrarFecha(Scanner input) {

        Date thisDate = null;
        boolean formatoCorrecto = true;
        // Utilizamos la clase DateFormat para establecer un formato para la hora y
        // fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        do {
            formatoCorrecto = true;
            System.out.println("\nEscriba la fecha de inicio del contrato en formato "
                    + "=>aaaa-MM-dd:");
            String fechaHora = input.nextLine();

            try {
                // Intentamos parsear el string que introduce el usuario y si falla es que está
                // mal, asi que repetimos
                thisDate = (Date) dateFormat.parse(fechaHora);
                //formatoCorrecto = validarFecha(thisDate);
                dateFormat.setLenient(false);
                dateFormat.parse(fechaHora);
                formatoCorrecto = true;
            } catch (ParseException ex) {
                formatoCorrecto = false;
            }
            if (!formatoCorrecto) {
                System.out.println("ERROR: Formato de fecha incorrecto...");
            }
        } while (!formatoCorrecto);

        return thisDate;
    }
}
