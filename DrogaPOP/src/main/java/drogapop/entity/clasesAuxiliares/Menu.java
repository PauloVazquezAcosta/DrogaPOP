package drogapop.entity.clasesAuxiliares;

import drogapop.HibernateUtil;
import drogapop.entity.Departamento;
import drogapop.entity.Empregado;
import drogapop.entity.Sede;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    /**
     * Determina la acción que realizará el programa en función de la selección del usuario en el menú
     *
     * @param opcion  Es la opción del menú que elige el usuario
     * @param entrada El Scanner que permitirá introducir opciones dentro del menú
     * @version 1.0.0
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
                HibernateUtil.mostrarTabla("Departamento");
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
                Departamento departamento = deparatamentoAintroducir(entrada);
                HibernateUtil.addObject(departamento);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 4:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("\nELIMINAR EMPLEADO ...");
                String empleadoDespedido = encontrarDNI(entrada);
                System.out.println(empleadoDespedido);
                if (!empleadoDespedido.equals("")) HibernateUtil.removeEmployee(empleadoDespedido);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 5:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("ELIMINAR DEPARTAMENTO ...");
                Departamento departamentoAeliminar = departamentoAeliminar(entrada);
                HibernateUtil.eliminarDepartamento(departamentoAeliminar);
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 6:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("MODIFICAR EMPLEADO");
                String dniEmpleadoAmodificar = encontrarDNI(entrada);
                if (!dniEmpleadoAmodificar.equals("")) {
                    Empregado empregado = HibernateUtil.buscarEmpleado(dniEmpleadoAmodificar);
                    Empregado empregadoModificado = empleadoAmodificar(entrada, empregado);
                    HibernateUtil.updateEmpleado(empregadoModificado);
                }
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 7:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("MOSTRANDO CONTRATOS");
                HibernateUtil.mostrarTabla("Contrato");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 8:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("MOSTRANDO SEDES");
                HibernateUtil.mostrarTabla("Sede");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 9:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("MOSTRANDO TIPOS DE CONTRATO");
                HibernateUtil.mostrarTabla("TipoContrato");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
            case 10:
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("GRACIAS POR UTILIZAR EL PROGRAMA, BUEN DÍA. VUELVE PRONTO!!!");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;
        }
        System.out.println("Pulsa Intro para continuar...");
        entrada.nextLine();
    }

    /**
     * Comprueba que existe un departamento a eliminar y lo devuelve
     *
     * @return departamentoAeliminar
     * @version 1.0.0
     */
    private static Departamento departamentoAeliminar(Scanner entrada) {
        Departamento departamentoAeliminar = new Departamento();
        int numeroDeDepartamento;
        boolean numeroCorrecto = false;
        ArrayList<Integer> idDepartamentos = new ArrayList<>(HibernateUtil.idDepartamentos());
        do {
            try {
                HibernateUtil.mostrarIdNombreDepartamentos();
                System.out.println("Introduzca el número de departamento dentro de los que ya existen: ");
                numeroDeDepartamento = entrada.nextInt();
            } catch (InputMismatchException ime) {
                entrada.nextLine();
                System.out.println(ime);
                numeroDeDepartamento = 0;
            }
            if (idDepartamentos.contains(numeroDeDepartamento)) {
                numeroCorrecto = true;
            } else {
                System.out.println("El departamento que ha introducido no existe en la base de datos");
            }
        } while (numeroDeDepartamento == 0 || !numeroCorrecto);
        departamentoAeliminar = HibernateUtil.buscarDepartamento(numeroDeDepartamento);
        return departamentoAeliminar;
    }

    /**
     * Se muestran los empleados existentes y se pregunta al usuario  qué  empleado desea eliminar
     * Se  comprueba que el DNI exista en la base de datos y si existe se devuelve
     *
     * @return dniIntroducido
     * @version 1.0.0
     */
    @Transactional
    private static String encontrarDNI(Scanner entrada) {
        ArrayList<String> dniList = new ArrayList<>(HibernateUtil.arrayDNIs());
        String dniIntroducido;
        boolean dniNoExiste = false;
        HibernateUtil.mostrarTabla("Empregado");
        do {
            dniNoExiste = false;
            System.out.println("Introduzca el DNI de un empleado que desee eliminar/modificar (intro para cancelar) :");
            dniIntroducido = entrada.nextLine();
            if (dniIntroducido.equals("")) {
                System.out.println("Operación cancelada, regresando al menú principal\n");
            } else {
                if (!dniList.contains(dniIntroducido)) {
                    dniNoExiste = true;
                    System.out.println("El DNI que ha introducido no existe en la base de datos");
                }
            }
        } while (!dniIntroducido.equals("") && dniNoExiste);
        return dniIntroducido;
    }

    /**
     * Se piden al usuario cada uno de los valores del departamento a introducir
     * validando en cada caso
     *
     * @return departamento
     * @version 1.0.0
     */
    @Transactional
    private static Departamento deparatamentoAintroducir(Scanner entrada) {
        int numeroSede, jefe;
        boolean numeroCorrecto;
        String nombre, numeroTelefono;
        //introducir nombre
        nombre = Letras.setDatos(entrada, "nombre");
        // Introducir sede
        do {
            numeroCorrecto = false;
            //recuperamos en una lista los id de las sedes
            ArrayList<Integer> idSedes = new ArrayList<>(HibernateUtil.idSedes());
            try {
                System.out.println("Introduzca el número de la sede dentro de los que ya existen: ");
                HibernateUtil.mostrarIdUbicacionSedes();
                numeroSede = entrada.nextInt();
            } catch (InputMismatchException ime) {
                entrada.nextLine();
                System.out.println(ime);
                numeroSede = 0;
            }
            if (idSedes.contains(numeroSede)) {
                numeroCorrecto = true;
            }
        } while (numeroSede == 0 || !numeroCorrecto);
        //introducir número de teléfono
        do {
            numeroCorrecto = true;
            try {
                System.out.print("Introduzca el número de teléfono del departamento: ");
                numeroTelefono = entrada.nextLine();
                entrada.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                System.out.println("ERROR : Ha introducido mal el número de teléfono");
                numeroTelefono = "";
            }
            if (!numeroTelefono.equals("")) {
                numeroCorrecto = Numero.comprobarNumero(numeroTelefono, 1);
            }
        } while (!numeroCorrecto || numeroTelefono.equals(""));
        Sede sede = HibernateUtil.buscarSede(numeroSede);
        Departamento departamento = new Departamento(nombre, null, sede, numeroTelefono);
        return departamento;
    }


    /**
     * Método que pide al usuario los datos de un empleado para después introducirlo en la base de datos
     *
     * @param entrada El Scanner que permitirá introducir los datos del empleado
     * @return employee
     * @version 1.0.0
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
        do {
            do {
                System.out.println("Escriba un dni: ");
                dni = entrada.nextLine();
                dniCorrecto = DNI.validarDNI(dni);
            } while (!dniCorrecto);
            // Comprobar si el DNI introducido existe en la base de datos
            ArrayList<String> dniList = new ArrayList<>(HibernateUtil.arrayDNIs());
            System.out.println(dniList);
            if (dniList.contains(dni)) {
                System.out.println("Este DNI ya existe en la base de datos");
                dni = "";
            }
        } while (dni.equals(""));
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
                entrada.nextLine();
                numeroDeDepartamento = 0;
            }
            if (idDepartamentos.contains(numeroDeDepartamento)) {
                numeroCorrecto = true;
            }
        } while (numeroDeDepartamento == 0 || !numeroCorrecto);
        // Introducir puesto de trabajo
        entrada.nextLine();
        cargo = Letras.setDatos(entrada, "puesto de trabajo");
        // Introducir numero de telefono
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
        Departamento dep = HibernateUtil.buscarDepartamento(numeroDeDepartamento);
        //instanciamos un empleado con los datos del usuario
        Empregado employee = new Empregado(dni, nome, apelidos, dep,
                cargo, numeroTelefono, dataNacemento, email, numeroSeguridadSocial);

        return employee;
    }

    /**
     * Se le ofrece un menú de opciones para modificar email o número de teléfono
     * del empleado que recibe por parámetros y se le setea el nuevo valor
     * devuelve un empleado modificado
     *
     * @param entrada   El Scanner que permitirá introducir los nuevos datos
     * @param empregado Empleado que se quiere modificar
     * @return empregado
     * @version 1.0.0
     */
    public static Empregado empleadoAmodificar(Scanner entrada, Empregado empregado) {
        int opcion;
        boolean numeroCorrecto;
        String numeroTelefono, email;
        do {
            System.out.println("Qué datos desea modificar: ");
            System.out.println("1. Modificar email");
            System.out.println("2. Modificar número de teléfono");
            System.out.println("3. Volver al menú principal");

            try {
                System.out.println("Elija una opción: ");
                opcion = Integer.parseInt(entrada.nextLine());

                if (opcion < 1 || opcion > 3) {
                    System.out.println("ERROR: Opción Inválida...");
                    System.out.print("Pulsa Intro para continuar...");
                    entrada.nextLine();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: Debe introducir solo números...");
                System.out.println("Pulsa Intro para continuar ...");
                entrada.nextLine();
                opcion = Integer.MIN_VALUE;
            }
        } while (opcion != 3 && opcion != 1 && opcion != 2);

        switch (opcion) {
            case 1:
                //Introducir email
                email = Letras.setDatos(entrada, "correo electronico");
                empregado.setEmail(email);
                break;
            case 2:
                // Introducir número de teléfono
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
                empregado.setNumeroTelefono(numeroTelefono);
                break;


        }
        return empregado;
    }
}
