package drogapop;

import drogapop.entity.*;
import drogapop.entity.clasesAuxiliares.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    /**
     * Crea la factoria de sesiones, que se utilizará para crear nuevas sesiones
     *
     * @version 0.0.1
     */
    public static void buildSessionFactory() {
        // Se instancia el objeto Configuration
        Configuration configuration = new Configuration();

        // Se almacenan las propiedades de la conexión en un objeto Properties
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://easybyte.club:2224/DrogaPOP");
        properties.put(Environment.USER, "javaconnect");
        properties.put(Environment.PASS, "conndb@Servo2021*");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        //  properties.put(Environment.HBM2DDL_AUTO, "create-drop");

        // Asignadas al objeto Configuration
        configuration.setProperties(properties);


        // Se registran las clases que hay que mapear con cada tabla de la base de datos
        configuration.addAnnotatedClass(Departamento.class);
        configuration.addAnnotatedClass(Empregado.class);
        configuration.addAnnotatedClass(Contrato.class);
        configuration.addAnnotatedClass(Sede.class);
        configuration.addAnnotatedClass(TipoContrato.class);

        // Se crea la SessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Abre una nueva sesión
     *
     * @version 0.0.1
     */
    public static void openSession() {
        session = sessionFactory.openSession();
    }

    /**
     * Devuelve la sesión actual. Si no hay una activa, se abre
     *
     * @return sesión actual
     * @version 0.0.1
     */
    public static Session getCurrentSession() {
        if ((session == null) || (!session.isOpen()))
            openSession();
        return session;
    }

    /**
     * Cierra Hibernate
     *
     * @version 0.0.1
     */
    public static void closeSessionFactory() {
        if (session != null)
            session.close();
        if (sessionFactory != null)
            sessionFactory.close();
    }

    /**
     * Introducir elemento en la base de datos
     *
     * @param object -> objeto a añadir a la base de datos
     * @version 0.0.1
     */
    public static void addObject(Object object) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Eliminar elemento de la base de datos
     *
     * @param object -> objeto a eliminar
     * @version 0.0.1
     */

    public static void removeObject(Object object) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Eliminar un empleado de la base de datos por ID
     *
     * @param identifier -> identificador del empleado
     * @version 0.0.1
     */
    @Transactional
    public static void removeEmployee(String identifier) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado WHERE DNI = '" + identifier + "'");
        Empregado empregado = (Empregado) query.getSingleResult();
        removeObject(empregado);
    }


    /**
     * devuelve un objeto tipo departamento según el id que pasemos por parámetro
     *
     * @return Departamento
     */
    @Transactional
    public static Departamento buscarDepartamento(Integer id) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento where id=" + id);
        Departamento departamento = (Departamento) query.getSingleResult();
        return departamento;
    }

    /**
     * devuelve un objeto tipo sede según el id que pasemos por parametro
     *
     * @return Sede
     */
    public static Sede buscarSede(Integer id) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede where id=" + id);
        Sede sede = (Sede) query.getSingleResult();

        return sede;
    }

    /**
     * Este método devuelve una lista con los codigods de los deparatamentos
     *
     * @return ArrayList<>-->idDepartamentos
     */
    public static ArrayList<Integer> idDepartamentos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();
        ArrayList<Integer> idDepartamentos = new ArrayList<>();
        for (Departamento departamento : departamentos) {
            idDepartamentos.add(departamento.getDeptno());
        }
        return idDepartamentos;
    }

    /**
     * Este método devuelve una lista con los códigos de las sedes
     * se usa para comprobar que al introducir un deparatamento pertenezca a una sede que ya exista
     *
     * @return ArrayList<>-->idSedes
     */
    public static ArrayList<Integer> idSedes() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede");
        ArrayList<Sede> sedes = (ArrayList<Sede>) query.list();
        ArrayList<Integer> idSedes = new ArrayList<>();
        for (int i = 0; i < sedes.size(); i++) {
            idSedes.add(sedes.get(i).getId());
        }
        return idSedes;
    }

    /**
     * este método muestra solo el id y el nombre del departamento.
     * es usado para que el usuario elija en que departamento va a incluir a un nuevo empleado
     */
    public static void mostrarIdNombreDepartamentos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();
        System.out.println("Código \tNombre");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento.getDeptno() + "\t\t" + departamento.getNome());
        }
    }

    /**
     * este método muestra solo el id y la ubicación de la sede.
     * es usado para que el usuario elija en que sede va a incluir a un nuevo departamento
     */
    public static void mostrarIdUbicacionSedes() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede");
        ArrayList<Sede> sedes = (ArrayList<Sede>) query.list();
        System.out.println("Código \tUbicación");
        for (Sede sede : sedes) {
            System.out.println(sede.getId() + "\t\t" + sede.getUbicacion());
        }
    }

    /**
     * Muestra los datos de la tabla que pasemos por parámetro
     * que debe ser un String con el nombre de la clase que modela a la tabla correspondiente en la BBDD
     * y emple el .toString de cada clase
     *
     * @version 0.0.1
     */
    public static void mostrarTabla(String tabla) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM " + tabla);
        ArrayList<Object> objetos = (ArrayList<Object>) query.list();
        for (Object objecto : objetos) {
            System.out.println(objecto.toString());
        }
    }

    /**
     * Este método devuelve un array con los DNI de cada uno de los empleado de la tabla empleados
     * se usa para comprobar que al insertar empleado no introduzcan un dni que ya exite en la BBDD
     *
     * @return ArrayList<String>
     */
    public static ArrayList<String> arrayDNIs() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado");
        ArrayList<String> dnis = new ArrayList<>();
        ArrayList<Empregado> empregados = (ArrayList<Empregado>) query.list();
        for (int i = 0; i < empregados.size(); i++) {
            dnis.add(empregados.get(i).getDNI());
        }
        return dnis;
    }

/**Se elimina un departamento recibido si no tiene empleados
 *
 * @param departamentoAeliminar
 * */
    public static void eliminarDepartamento(Departamento departamentoAeliminar) {
        int opcion = 2;
        boolean opcionCorrecta = false;
        List<Empregado> empregados = departamentoAeliminar.getEmpregados();
        System.out.println(empregados.toString());
        if (!departamentoAeliminar.getEmpregados().isEmpty()) {
            System.out.println("No se puede eliminar el departamento " + departamentoAeliminar.getNome()
                    + " porque tiene empleados asociados");
        } else {
            removeObject(departamentoAeliminar);
        }

    }
/**Se busca a un empleado usando su dni
 *
 * @param dniEmpleadoAmodificar
 * @return empregado
 * */
    public static Empregado buscarEmpleado(String dniEmpleadoAmodificar) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado WHERE DNI = '" + dniEmpleadoAmodificar + "'");
        Empregado empregado = (Empregado) query.getSingleResult();
        empregado.toString();
        return empregado;
    }
/**Recibe un empleado que ha sido modificado y lo acttualiza en la base de datos
 *
 * @param empregadoModificado 
 * */
    public static void updateEmpleado(Empregado empregadoModificado) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(empregadoModificado);
        session.getTransaction().commit();
        session.close();
    }
}
