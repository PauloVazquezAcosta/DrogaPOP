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
        // Utilizamos la clase DateFormat para establecer un formato para la hora y fecha
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        do {
            formatoCorrecto = true;
            System.out.println("\nEscriba la fecha de inicio del contrato en formato "
                    + "=>YYYY-MM-DD:");
            String fechaHora = input.nextLine();

            try {
                // Intentamos parsear el string que introduce el usuario y si falla es que está mal, asi que repetimos
                thisDate = (Date) dateFormat.parse(fechaHora);
                formatoCorrecto = validarFecha(fechaHora);
            } catch (ParseException ex) {
                formatoCorrecto = false;
            }
            if (!formatoCorrecto) {
                System.out.println("ERROR: Formato de fecha incorrecto...");
            }
        } while (!formatoCorrecto);

        return thisDate;
    }


    /**
     * Verifica si una fecha con el formato deseado es correcta.
     *
     * @param fechaHora Recibe una fecha que ya tiene el formato adecuado.
     * @return boolean true/OK false/error.
     * @version 0.0.1
     */
    public static boolean validarFecha(String fechaHora) {
        if (fechaHora == null) {
            return false;
        }
        try {
            // Separamos los datos del String introducido.
            int dia = Integer.valueOf(fechaHora.substring(8, 10));
            int mes = Integer.valueOf(fechaHora.substring(5, 7));
            int anio = Integer.valueOf(fechaHora.substring(0, 4));
            boolean anioBisciesto = false;

            // Validación Bisiesto.
            if ((anio % 4 == 0 && anio % 400 == 0 && anio % 100 == 0)
                    || (anio % 4 == 0 && anio % 100 != 0)) {
                anioBisciesto = true;
            }

            // Validación Día.
            if (dia < 1 || dia > 31) {
                return false;
            }
            // Validación Mes de febrero.
            if (mes == 2 && dia > 28 && !anioBisciesto) {
                return false;
            }
            // Validación Día 31.
            if ((mes == 2 || mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia == 31)) {
                return false;
            }
            // Validación Mes.
            if (mes < 1 || mes > 12) {
                return false;
            }
            /*Para saber si un año es bisiesto se puede aplicar una simple formula,
            la cual dice que un año es bisiesto si es divisible por cuatro,
            excepto los principios de año (los divisibles por 100),
            que para ser bisiestos deben de ser divisibles también por 400.*/
            if (mes == 2 && dia == 29 && !anioBisciesto) {
                return false;
            }

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }

        // Fecha válida
        return true;
    }

}
