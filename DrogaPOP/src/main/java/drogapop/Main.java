/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drogapop;


import drogapop.entity.Empregado;
import drogapop.entity.clasesAuxiliares.DNI;
import drogapop.entity.clasesAuxiliares.Fecha;
import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
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
            System.out.println("0. Mostrar la tabla emplados.");
            System.out.println("1. Mostrar la tabla departamentos.");
            System.out.println("2. Introducir empleado.");
            System.out.println("3. Introducir departamento.");
            System.out.println("4. Eliminar empleado.");
            System.out.println("5. Eliminar departamento.");
            System.out.println("6. Salir.");

            try {
                System.out.println("Elija una opcion: ");
                opcion = Integer.parseInt(input.nextLine());

                if (opcion < 0 || opcion > 6) {
                    System.out.println("ERROR: Opción Inválida...");
                    System.out.print("Pulsa Intro para continuar...");
                    input.nextLine();
                } else {
                    menuOpciones(opcion, input);
                }

            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: Debe introducir solo números...");
                System.out.println("Pulsa Intro para continuar ...");
                input.nextLine();
                opcion = Integer.MIN_VALUE;
            }

        } while (opcion != 6);
        // Se cierra la conexión a la BBDD y la entrada por teclado.
        input.close();

    }

    /**
     *  Determina la acción que realizará el programa en función de la selección del usuario en el menú
     * @param opcion
     * @param entrada
     */
    private static void menuOpciones(int opcion, Scanner entrada) {
        switch (opcion) {
            case 0:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nMOSTRANDO EMPLEADOS ... ");
                HibernateUtil.mostrarTablaEmpleados();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 1:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nMOSTRANDO DEPARTAMENTOS ... ");

            HibernateUtil.mostrarTablaDepartamentos();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nINTRODUCIR EMPLEADO ... ");
                Empregado employee = introducirEmpleado(entrada);
                HibernateUtil.addObject(employee);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 3:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nINTRODUCIR DEPARTAMENTO ...");
                //prepararNuevoVuelo(input);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 4:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nELIMINAR EMPLEADO ...");
                //Queries.verVuelosCreados(bbdd, input);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 5:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("ELIMINAR DEPARTAMENTO ...");
                //Queries.cambiarFumadores(bbdd, input);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 6:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("GRACIAS POR UTILIZAR EL PROGRAMA, BUEN DÍA. VUELVE PRONTO!!!");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
        }
        System.out.println("Pulsa Intro para continuar...");
        entrada.nextLine();
    }


    /**
     * Metodo que pide al usuario los datos de un empleado para después introducirlo en la base de datos
     * @param entrada
     * @version 0.0.1
     * @return employee
     */
    public static Empregado introducirEmpleado(Scanner entrada){

        String dni, nome, apelidos,cargo,numeroTelefono,email,numeroSeguridadeSocial;
        int numeroDeDepartamento=1 ;
        cargo="tipo";
        numeroTelefono="604";
        Date dataNacemento =new Date(1-3-2021);
        email="@gmail" ;
        numeroSeguridadeSocial ="1234";

        // Variables auxiliares
        boolean dniCorrecto;
        do {
            System.out.println("Escriba un dni: ");
            dni = entrada.nextLine();
            dniCorrecto= DNI.validarDNI(dni);
        } while (!dniCorrecto);

        nome = setDatos(entrada, "nombre");
        apelidos = setDatos(entrada, "apellidos");

        dataNacemento = Fecha.entrarFecha(entrada);


        Empregado employee = new Empregado(500,dni, nome, apelidos,numeroDeDepartamento, cargo,numeroTelefono , dataNacemento, email,numeroSeguridadeSocial);

        return employee;
    }



    /**
     * Veririca que el usuario introduzca una cadena válida.
     * @param entrada traspasa el Scanner asociado al teclado.
     * @version 0.0.1
     * @return String validado
     */
    public static String setDatos(@org.jetbrains.annotations.NotNull Scanner entrada, String tipoDato) {
        String dato;
        Matcher matcher;
        String formato = "[A-Z]{2,254}";
        do {
            System.out.print("Introduza el "+tipoDato+" :");
            dato = entrada.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile(formato);
            matcher = pattern.matcher(dato);

        } while (!matcher.matches());

        return dato;
    }




}
