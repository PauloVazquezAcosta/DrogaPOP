package drogapop.entity.clasesAuxiliares;

import drogapop.HibernateUtil;
import drogapop.entity.Departamento;
import drogapop.entity.Empregado;


import java.util.ArrayList;
import java.util.Date;
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
                Departamento departamento = introducirDeparatamento(entrada);
                HibernateUtil.addObject(departamento);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 4:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nELIMINAR EMPLEADO ...");
                Empregado empleadoDespedido = eliminarEmpleado(entrada);
                HibernateUtil.removeObject(empleadoDespedido);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 5:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("ELIMINAR DEPARTAMENTO ...");
                Departamento departamentoEliminado = eliminarDepartamento(entrada);
                HibernateUtil.removeObject(departamentoEliminado);
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

    private static Departamento eliminarDepartamento(Scanner entrada) {
        Departamento deparatamentoEliminado = new Departamento();
        return deparatamentoEliminado;
    }

    private static Empregado eliminarEmpleado(Scanner entrada) {
        Empregado empregadoDespedido = new Empregado();
        return empregadoDespedido;
    }

    private static Departamento introducirDeparatamento(Scanner entrada) {
        Departamento departamento = new Departamento();
        return departamento;
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

        //Introducir DNI
        do {
            System.out.println("Escriba un dni: ");
            dni = entrada.nextLine();
            dniCorrecto = DNI.validarDNI(dni);
        } while (!dniCorrecto);
        //introducir nombre y apellidos
        nome = Letras.setDatos(entrada, "nombre");
        apelidos = Letras.setDatos(entrada, "apellidos");
        // Introducir número de departamento
        do {
            numeroCorrecto = false;
            //recuperamos en una lista los id de los departamentos
            ArrayList<Integer> idDepartamentos = new ArrayList<>(HibernateUtil.idDepartamentos());
            try {
                System.out.println("Introduzca el número de departamento dentro de los que ya existen: ");
                //aqui debemos mostrar los departamentos  y comprobar que el número introducido por el usuario es un deparatamento que existe
                HibernateUtil.mostrarIdNombreDepartamentos();
                numeroDeDepartamento = entrada.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                numeroDeDepartamento = 0;
            }
            if (idDepartamentos.contains(numeroDeDepartamento)) {
                numeroCorrecto = true;
            }
        } while (numeroDeDepartamento == 0 || !numeroCorrecto);

        // Introducir puesto de trabajo
        cargo = Letras.setDatos(entrada, "puesto de trabajo");
        // Introducir numero de telefono
        /* En caso de que a hora de introducir o numero da seguridad social solo comprobemos que teña unha
         *  certa cantidade de numeros (como e no caso de este do-while) poderemos introducir este codigo
         *  en un metodo aparte, e en funcion do parametro que se lle pase realizar unha comprobacion de
         *  9  numeros en caso do numero de telefono ou unha comprobacion de 12 numeros para a seguridad
         *  social. Algo parecido ao que facemos no metodo setDatos*/
        do {
            numeroCorrecto = true;
            try {
                System.out.print("Introduzca el número de teléfono del empleado: ");
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

        //Introducir fecha de nacimiento
        dataNacemento = Fecha.entrarFecha(entrada);
        //Introducir email
        email = Letras.setDatos(entrada, "correo electronico");

        // Introducir numero de la seguridad social
        do {
            numeroCorrecto = true;
            try {
                System.out.print("Introduza el número de la seguridad social: ");
                numeroSeguridadSocial = entrada.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el número de la seguridad social");
                numeroSeguridadSocial = "";
            }
            if (!nome.equals("")) {
                numeroCorrecto = Numero.comprobarNumero(numeroSeguridadSocial, 2);
            }
        } while (!numeroCorrecto || numeroSeguridadSocial.equals(""));


        //instanciamos un empleado con los datos del usuario
        Empregado employee = new Empregado( dni, nome, apelidos, numeroDeDepartamento,
                cargo, numeroTelefono, dataNacemento, email, numeroSeguridadSocial);

        return employee;
    }


}
