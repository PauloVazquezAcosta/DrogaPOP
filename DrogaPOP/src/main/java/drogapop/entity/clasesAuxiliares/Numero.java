package drogapop.entity.clasesAuxiliares;

public class Numero {
    public static boolean comprobarNumero(String numeroIntroducido, int tipoNumero) {
        // Si pasamos un 1 comprobaremos la longitud de un número de teléfono
        // Si pasamos un 2 comprobaremos la longitud de un número de la seguridad social
        boolean correcto = true;
        int longitudNumero = 0;

        switch (tipoNumero) {
            case 1:
                longitudNumero = 9;
                break;
            case 2:
                longitudNumero = 12;
                break;
        }

        if (numeroIntroducido.length() != longitudNumero) {
            correcto = false;
        }

        return correcto;
    }
}
