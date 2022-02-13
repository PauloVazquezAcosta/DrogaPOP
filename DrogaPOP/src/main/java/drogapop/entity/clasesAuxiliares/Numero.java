package drogapop.entity.clasesAuxiliares;

public class Numero {
    /**
     * Comprueba si el número introducido tiene la longitud de número de teléfono o seguridad social en función del caso
     *
     * @param numeroIntroducido Número a comprobar su longitud
     * @param tipoNumero        Indica si es teléfono o número de la seguridad social
     * @return boolean
     * @version 1.0.0
     */
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
