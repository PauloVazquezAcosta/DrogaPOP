/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drogapop;
import drogapop.entity.Empregado;
import drogapop.entity.clasesAuxiliares.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Paulo
 * @author Yuda
 * @author Maikel
 * @author Nicolás
 */
public class Main {

    public static void main(String[] args) {

        HibernateUtil.buildSessionFactory();
        Scanner input = new Scanner(System.in);
        int opcion;

        // Se muestra el menú hasta que se introduzca una opción valida o se salga del programa.
        do {
            System.out.println("*************SISTEMA DE GESTIÓN DROGAPOP*************");
            System.out.println("0. Mostrar la tabla empleados.");
            System.out.println("1. Mostrar la tabla departamentos.");
            System.out.println("2. Introducir empleado.");
            System.out.println("3. Introducir departamento.");
            System.out.println("4. Eliminar empleado.");
            System.out.println("5. Eliminar departamento.");
            System.out.println("6. Modificar empleado");
            System.out.println("7. Salir.");

            try {
                System.out.println("Elija una opcion: ");
                opcion = Integer.parseInt(input.nextLine());

                if (opcion < 0 || opcion > 7) {
                    System.out.println("ERROR: Opción Inválida...");
                    System.out.print("Pulsa Intro para continuar...");
                    input.nextLine();
                } else {
                    Menu.menuOpciones(opcion, input);
                }

            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: Debe introducir solo números...");
                System.out.println("Pulsa Intro para continuar ...");
                input.nextLine();
                opcion = Integer.MIN_VALUE;
            }

        } while (opcion != 7);
        // Se cierra la conexión a la BBDD y la entrada por teclado.
        input.close();
    }


}
