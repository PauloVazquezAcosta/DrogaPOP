package drogapop.entity.clasesAuxiliares;

import drogapop.HibernateUtil;
import drogapop.entity.Empregado;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    /**
     * Determina la acción que realizará el programa en función de la selección del usuario en el menú
     *
     * @param opcion
     * @param entrada
     */
    public static void menuOpciones(int opcion, Scanner entrada) {
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
                //Departamento departamento=introducirDeparatamento(entrada);
                //HibernateUtil.addObject(departamento);
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
                // Empregado employee=eliminarEmpleado(entrada);
                //HibernateUtil.removeObject(employee);
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
     *
     * @param entrada
     * @return employee
     * @version 0.0.1
     */
    public static Empregado introducirEmpleado(Scanner entrada) {

        String dni, nome, apelidos, cargo, numeroTelefono, email, numeroSeguridadSocial;
        int numeroDeDepartamento = 1;
        cargo = "tipo";
        numeroTelefono = "604";
        Date dataNacemento = new Date(1 - 3 - 2021);
        email = "@gmail";
        numeroSeguridadSocial = "1234";

        // Variables auxiliares
        boolean dniCorrecto, numeroCorrecto;
        ;
        do {
            System.out.println("Escriba un dni: ");
            dni = entrada.nextLine();
            dniCorrecto = DNI.validarDNI(dni);
        } while (!dniCorrecto);

        nome = Letras.setDatos(entrada, "nombre");
        apelidos = Letras.setDatos(entrada, "apellidos");
        dataNacemento = Fecha.entrarFecha(entrada);
        email = Letras.setDatos(entrada, "correo electronico");


        // Introducir numero de telefono
        /* En caso de que a hora de introducir o numero da seguridad social solo comprobemos que teña unha
         *  certa cantidade de numeros (como e no caso de este do-while) poderemos introducir este codigo
         *  en un metodo aparte, e en funcion do parametro que se lle pase realizar unha comprobacion de
         *  9  numeros en caso do numero de telefono ou unha comprobacion de 12 numeros para a seguridad
         *  social. Algo parecido ao que facemos no metodo setDatos*/
        do {
            numeroCorrecto = true;
            try {
                System.out.println("Introduzca el número de teléfono del empleado");
                numeroTelefono = entrada.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el número de teléfono");
                numeroTelefono = "";
            }

            if (numeroTelefono.equals("")) {
                numeroCorrecto = Numero.comprobarNumero(numeroTelefono, 1);
            }
        } while (!numeroCorrecto || numeroTelefono.equals(""));


        // Introducir numero de la seguridad social
        do {
            numeroCorrecto = true;
            try {
                System.out.println("Introduza el número de la seguridad social");
                numeroSeguridadSocial = entrada.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el numero de la seguridad social");
                numeroSeguridadSocial = "";
            }

            if (!nome.equals("")) {
                numeroCorrecto = Numero.comprobarNumero(numeroSeguridadSocial, 2);
            }
        } while (!numeroCorrecto || numeroSeguridadSocial.equals(""));

        // Introducir numero de departamento
        do {
            try {
                System.out.println("Introduzca el número de departamento");
                numeroDeDepartamento = entrada.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                numeroDeDepartamento = 0;
            }
        } while (numeroDeDepartamento == 0);

        // Introducir puesto de trabajo
        cargo = Letras.setDatos(entrada, "puesto de trabajo");


        Empregado employee = new Empregado(500, dni, nome, apelidos, numeroDeDepartamento,
                cargo, numeroTelefono, dataNacemento, email, numeroSeguridadSocial);

        return employee;
    }


}
