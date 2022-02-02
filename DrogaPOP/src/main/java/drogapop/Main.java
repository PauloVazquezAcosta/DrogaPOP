/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drogapop;


import drogapop.entity.Empregado;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//import java.sql.Date;


import org.hibernate.internal.build.AllowSysOut;

import javax.xml.bind.SchemaOutputResolver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import drogapop.entity.Empregado;

import java.util.Scanner;

/**
 *
 * @author Paulo
 * @author Yuda
 * @author Maikel
 * @author Nicolás
 */
public class Main {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void main(String[] args) {

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
     *  Determina la accion que realizara el programa en funcion de la seleccion del usuario en el menu
     * @param opcion
     * @param entrada
     */
    private static void menuOpciones(int opcion, Scanner entrada) {
        switch (opcion) {
            case 0:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nMOSTRANDO EMPLEADOS ... ");
                //HibernateUtil.mostrarTablaEmpleados();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 1:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nMOSTRANDO DEPARTAMENTOS ... ");
                //HibernateUtil.mostrarTablaDepartamentos();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nINTRODUCIR EMPLEADO ... ");
                Empregado employee = introducirEmpleado(entrada);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 3:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nINTRODUCIR DEPARTAMENTO ...");
                //HibernateUtil.introducirDepartamento();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 4:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nELIMINAR EMPLEADO ...");
                //HibernateUtil.eliminarEmpleado();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 5:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("ELIMINAR DEPARTAMENTO ...");
                //HibernateUtil.eliminarDepartamento();
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
     * Metodo que pide al usuario los datos de un empleado para despues introducirlo en la base de datos
     * @param entrada
     * @version 0.0.1
     * @return employee
     */
    public static Empregado introducirEmpleado(Scanner entrada){
        String dni, nome, apelidos, email, numeroTelefono, numeroSeguridadSocial,postoTraballo;/*contratoDende*/
        int deptno;
        Date dataNacemento;
        float salario;
        // Variables auxiliares
        boolean dniCorrecto, numeroCorrecto;


        do {
            System.out.println("Escriba un dni: ");
            dni = entrada.nextLine();
            dniCorrecto=validarDNI(dni);

        } while (!dniCorrecto);

        nome = setDatos(entrada, "nombre");
        apelidos = setDatos(entrada, "apellidos");
        //contratoDende = entrarFecha(entrada).toString();
        dataNacemento = entrarFecha(entrada);
        email = setDatos(entrada, "correo electronico");

        // Introducir numero de telefono
        /* En caso de que a hora de introducir o numero da seguridad social solo comprobemos que teña unha
        *  certa cantidade de numeros (como e no caso de este do-while) poderemos introducir este codigo
        *  en un metodo aparte, e en funcion do parametro que se lle pase realizar unha comprobacion de
        *  9  numeros en caso do numero de telefono ou unha comprobacion de 12 numeros para a seguridad
        *  social. Algo parecido ao que facemos no metodo setDatos*/
        do{
            numeroCorrecto = true;
            try{
                System.out.println("Introduza el numero de telefono del empleado");
                numeroTelefono = entrada.nextLine();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el numero de telefono");
                numeroTelefono = "";
            }

            if(!Objects.equals(numeroTelefono,"")){
                numeroCorrecto = comprobarNumero(numeroTelefono, 1);
            }
        }while(!numeroCorrecto || numeroTelefono.equals(""));

        // Introducir numero de la seguridad social
        do{
            numeroCorrecto = true;
            try{
                System.out.println("Introduza el numero de la seguridad social");
                numeroSeguridadSocial = entrada.nextLine();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el numero de la seguridad social");
                numeroSeguridadSocial = "";
            }

            if(!Objects.equals(numeroSeguridadSocial,"")){
                numeroCorrecto = comprobarNumero(numeroSeguridadSocial, 2);
            }
        }while(!numeroCorrecto || numeroSeguridadSocial.equals(""));

        // Introducir numero de departamento
        do{
            try{
                System.out.println("Introduza el numero de departamento");
                deptno = entrada.nextInt();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                deptno = 0;
            }
        }while(deptno == 0);

        // Introducir puesto de trabajo
        postoTraballo = setDatos(entrada, "puesto de trabajo");

        // Introducir salario
        /*
        do{
            try{
                System.out.println("Introduza el salario del trabajador");
                salario = entrada.nextFloat();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                salario = 0;
            }
        }while(salario == 0);

         */
        
        // Aclarar si o id o vai a establecer a base de datos ou o vamos a generar con un random dentro
        // do programa comprobando que non existe dentro da base de datos previamente

        // Añadir 'contratoDende' ao contructor de empleado e a base de datos enc caso de que non este

        Empregado employee = new Empregado(500,dni, nome, apelidos,deptno, postoTraballo,numeroTelefono , dataNacemento, email,numeroSeguridadSocial);

        return employee;
    }


    /**
     * Solicita al usuario introducir una fecha y valida que esta este en el
     * formato deseado.
     *
     * @param input traspasa el Scanner asociado al teclado.
     * @version 0.0.1
     * @return Date devuelve un objeto Date válido
     */
    public static Date entrarFecha(Scanner input) {

        Date thisDate = null;
        boolean formatoCorrecto;
        // Utilizamos la clase DateFormat para establecer un formato para la hora y fecha
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

        do {
            formatoCorrecto = true;
            System.out.println("\nEscriba la fecha de nacimiento del empleado en formato "
                    + "=>YYYY-MM-DD:");
            String fechaHora = input.nextLine();

            try {
                // Intentamos parsear el string que introduce el usuario y si falla es que esta mal, asi que repetimos
                thisDate = (Date) dateFormat.parse(fechaHora);
                formatoCorrecto = validarFecha(fechaHora);
            }catch (InputMismatchException | ParseException | ClassCastException exception){
                formatoCorrecto = false;
                System.out.println("ERROR: Formato de fecha incorrecto...");
                System.out.println(exception);
            }
        } while (!formatoCorrecto);

        return thisDate;
    }

    /**
     * Valida que el DNI introducido sea correcto
     * @param dni
     * @return booelean
     */
    public static boolean validarDNI(String dni) {

        final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };

        return Arrays.binarySearch(INVALIDOS, dni) < 0 // (1)
                && REGEXP.matcher(dni).matches() // (2)
                && dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23); // (3)
    }

    /**
     * Verifica si una fecha con el formato deseada es correcta.
     *
     * @param fechaHora Recibe una fecha que ya tiene el formato adecuado.
     * @version 0.0.1
     * @return boolean true/OK false/error.
     */
    public static boolean validarFecha(String fechaHora) {
        if (fechaHora == null) {
            return false;
        }
        try {
            // Separamos los datos del String introducido.
            int dia = Integer.parseInt(fechaHora.substring(8, 10));
            int mes = Integer.parseInt(fechaHora.substring(5, 7));
            int anio = Integer.parseInt(fechaHora.substring(0, 4));
            boolean anioBisiesto = false;

            // Validación Bisiesto.
            if ((anio % 4 == 0 && anio % 400 == 0 && anio % 100 == 0)
                    || (anio % 4 == 0 && anio % 100 != 0)) {
                anioBisiesto = true;
            }

            // Validación Día.
            if (dia < 1 || dia > 31) {
                return false;
            }
            // Validación Mes de febrero.
            if (mes == 2 && dia > 28 && !anioBisiesto) {
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
            if (mes == 2 && dia == 29 && !anioBisiesto) {
                return false;
            }

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }

        // Fecha válida
        return true;
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
        String formato="";
        switch (tipoDato){
            case "apellidos":
                formato = "[A-Z]{2,254}"+" "+"[A-Z]{2,254}";
                break;
            case "nombre":
                formato = "[A-Z]{2,254}";
                break;
            case "correo electronico":
                formato = "[A-Z]{2,254}"+"@"+"[A-Z]{2,50}"+"."+"[A-Z]{2,6}";
                break;
            case "puesto de trabajo":
                formato = "[A-Z]{2,254}";
                break;
        }

        do {
            System.out.print("Introduza "+tipoDato+" :");
            dato = entrada.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile(formato);
            matcher = pattern.matcher(dato);

        } while (!matcher.matches());

        return dato;
    }

    public static boolean comprobarNumero(String numeroIntroducido, int tipoNumero){
        // Si pasamos un 1 comprobaremos la longitud de un numero de telefono
        // Si pasamos un 2 comprobaremos la longitud de un numero de la seguridad social
        boolean correcto = true;
        int longitudNumero=0;

        switch (tipoNumero){
            case 1:
                longitudNumero = 9;
                break;
            case 2:
                longitudNumero = 12;
                break;
        }

        if(numeroIntroducido.length() != longitudNumero){
            correcto = false;
        }

        return correcto;
    }




}
