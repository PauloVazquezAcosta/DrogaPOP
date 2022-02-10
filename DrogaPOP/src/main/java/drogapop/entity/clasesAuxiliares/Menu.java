package drogapop.entity.clasesAuxiliares;

import drogapop.HibernateUtil;
import drogapop.entity.Departamento;
import drogapop.entity.Empregado;


import javax.transaction.Transactional;
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
                HibernateUtil.mostrarTabla("Empregado");
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
                String empleadoDespedido = eliminarEmpleado(entrada);
                System.out.println(empleadoDespedido);
                if(!empleadoDespedido.equals("")) HibernateUtil.removeEmployee(empleadoDespedido);
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
        //debemos comprobar que existe y que no tenga empleados asociados
        return deparatamentoEliminado;
    }

    @Transactional
    private static String eliminarEmpleado(Scanner entrada) {
        ArrayList<String> dniList = new ArrayList<>(HibernateUtil.arrayDNIs());
        String dniIntroducido;
        boolean dniNoExiste = false;
        // Mostramos los empleados existentes y preguntamos al usuario  que  empleado desea eliminar
        HibernateUtil.mostrarTabla("Empregado");

        do{
            dniNoExiste = false;
            System.out.println("Introduza el DNI de un empleado que desee eliminar (intro para cancelar) :");
            dniIntroducido = entrada.nextLine();

            if(dniIntroducido.equals("")){
                System.out.println("Eliminacion cancelada, regresando al menu principal\n");
            }else{
                // Comprobar si el DNI introducido existe en la base de datos
                if(!dniList.contains(dniIntroducido)){
                    dniNoExiste = true;
                    System.out.println("El DNI que ha introducido no existe en la base de datos");
                }
            }

        }while(!dniIntroducido.equals("") && dniNoExiste);


        return dniIntroducido;
    }

    private static Departamento introducirDeparatamento(Scanner entrada) {
        Departamento departamento = new Departamento();
        int numeroSede;
        boolean numeroCorrecto;

        //String nome, int xefe, int ubicacion, String telefono
        /**El atributo ubicacion en la tabala deparatamento es un número pero debemos mostrar
         * la tabla sedes para que el usuario introduzca un codigo de sede dentro de los que existen
         * */

        do {
            numeroCorrecto = false;
            //recuperamos en una lista los id de las sedes
            ArrayList<Integer> idSedes = new ArrayList<>(HibernateUtil.idSedes());
            try {
                System.out.println("Introduzca el número de la sede dentro de los que ya existen: ");
                //aqui debemos mostrar las sedes  y comprobar que el número introducido por el usuario es una que ya existe que existe
                HibernateUtil.mostrarIdUbicacionSedes();
                numeroSede = entrada.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                numeroSede= 0;
            }
            if (idSedes.contains(numeroSede)) {
                numeroCorrecto = true;
            }
        } while (numeroSede == 0 || !numeroCorrecto);


        /* aqui debemos preguntar a que empleado pondra de jefe y mostrar a los que ya son jefes de otros departamentos
        **/


        return departamento;
    }


    /**
     * Metodo que pide al usuario los datos de un empleado para después introducirlo en la base de datos
     *
     * @param entrada
     * @return employee
     * @version 0.0.1
     */
    @Transactional
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
        do{
            do {
                System.out.println("Escriba un dni: ");
                dni = entrada.nextLine();
                dniCorrecto = DNI.validarDNI(dni);
            } while (!dniCorrecto);

            // Comprobar si el DNI introducido existe en la base de datos
            ArrayList<String> dniList = new ArrayList<>(HibernateUtil.arrayDNIs());
            System.out.println(dniList);

            if(dniList.contains(dni)){
                System.out.println("Este DNI ya existe en la base de datos");
                dni = "";
            }

        }while(dni.equals(""));

        //introducir nombre y apellidos
        nome = Letras.setDatos(entrada, "nombre");
        apelidos = Letras.setDatos(entrada, "apellidos");
        // Introducir número de departamento
        do {
            numeroCorrecto = false;
            //recuperamos en una lista los id de los departamentos
            ArrayList<Integer> idDepartamentos = new ArrayList<>(HibernateUtil.idDepartamentos());
            try {
                //aqui debemos mostrar los departamentos  y comprobar que el número introducido por el usuario es un deparatamento que existe
                HibernateUtil.mostrarIdNombreDepartamentos();
                System.out.println("Introduzca el número de departamento dentro de los que ya existen: ");
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
        // Vaciamos la cache del scanner
        entrada.nextLine();
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

            if (!numeroTelefono.equals("")) {
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
            if (!numeroSeguridadSocial.equals("")) {
                numeroCorrecto = Numero.comprobarNumero(numeroSeguridadSocial, 2);
            }
        } while (!numeroCorrecto || numeroSeguridadSocial.equals(""));
Departamento dep=HibernateUtil.buscarDepartamento(numeroDeDepartamento);

        //instanciamos un empleado con los datos del usuario
        Empregado employee = new Empregado( dni, nome, apelidos, dep,
                cargo, numeroTelefono, dataNacemento, email, numeroSeguridadSocial);

        return employee;
    }


}
