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
import java.util.*;
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
     * Metodo que pide al usuario los datos de un empleado para después introducirlo en la base de datos
     * @param entrada
     * @version 0.0.1
     * @return employee
     */
    public static Empregado introducirEmpleado(Scanner entrada){


        String dni, nome, apelidos,cargo,numeroTelefono,email,numeroSeguridadSocial;
        int numeroDeDepartamento=1 ;
        cargo="tipo";
        numeroTelefono="604";
        Date dataNacemento =new Date(1-3-2021);
        email="@gmail" ;
        numeroSeguridadSocial ="1234";

        // Variables auxiliares
        boolean dniCorrecto ,numeroCorrecto;;
        do {
            System.out.println("Escriba un dni: ");
            dni = entrada.nextLine();
            dniCorrecto= DNI.validarDNI(dni);
        } while (!dniCorrecto);

        nome = setDatos(entrada, "nombre");
        apelidos = setDatos(entrada, "apellidos");
        dataNacemento = Fecha.entrarFecha(entrada);
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
                System.out.println("Introduzca el número de teléfono del empleado");
                numeroTelefono = entrada.nextLine();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el número de teléfono");
                numeroTelefono = "";
            }

            if(numeroTelefono.equals("")){
                numeroCorrecto = comprobarNumero(numeroTelefono, 1);
            }
        }while(!numeroCorrecto || numeroTelefono.equals(""));


        // Introducir numero de la seguridad social
        do{
            numeroCorrecto = true;
            try{
                System.out.println("Introduza el número de la seguridad social");
                numeroSeguridadSocial = entrada.nextLine();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el numero de la seguridad social");
                numeroSeguridadSocial = "";
            }

            if(!nome.equals("")){
                numeroCorrecto = comprobarNumero(numeroSeguridadSocial, 2);
            }
        }while(!numeroCorrecto || numeroSeguridadSocial.equals(""));

        // Introducir numero de departamento
        do{
            try{
                System.out.println("Introduzca el número de departamento");
                numeroDeDepartamento = entrada.nextInt();
            }catch (InputMismatchException ime){
                System.out.println(ime);
                numeroDeDepartamento = 0;
            }
        }while(numeroDeDepartamento == 0);

        // Introducir puesto de trabajo
        cargo = setDatos(entrada, "puesto de trabajo");


        Empregado employee = new Empregado(500,dni, nome, apelidos,numeroDeDepartamento,
                cargo,numeroTelefono , dataNacemento, email,numeroSeguridadSocial);

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
