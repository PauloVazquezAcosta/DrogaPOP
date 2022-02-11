package drogapop;

import drogapop.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;

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
  public static void removeEmployee(String identifier) {
      Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado WHERE DNI = '" + identifier+"'");
      Empregado empregado = (Empregado) query.getSingleResult();
      removeObject(empregado);
      /*for (Empregado empregado : empregados) {
          removeObject(empregado);
      }
       */
  }

    /**
     * Muestra los datos de la tabla Empregados
     * usando una query y guardando los datos de la BD en un ArrayList
     * que se recorre con un for each mostrando por pantalla los atributos de cada objeto Empregado
     */
    public static void mostrarTablaEmpleados() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado");
        ArrayList<Empregado> empregados = (ArrayList<Empregado>) query.list();

        System.out.println("id\tdni\tnome\tapelidos\tdptno\tcargo\ttelefono\tdata_nacemento\temail\tnum_seg_soc");
        for (Empregado empregado : empregados) {
            System.out.println(empregado.getId() + "\t" + empregado.getDNI() + "\t" + empregado.getNome() + "\t" + empregado.getApelidos() + "\t" +
                     empregado.getCargo() + "\t" + empregado.getNumeroTelefono() + "\t" +
                    empregado.getDataNacemento() + "\t" + empregado.getEmail() + "\t" + empregado.getNumeroSeguridadeSocial());
        }
    }

    public static Departamento buscarDepartamento(Integer id) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento where id="+id);
        Departamento departamento = (Departamento) query.getSingleResult();

        return departamento;
    }
    public static Sede buscarSede(Integer id) {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede where id="+id);
        Sede sede = (Sede) query.getSingleResult();

        return sede;
    }
    /**
     * Muestra los datos de la tabla Departamento
     * usando una query y guardando los datos de la BD en un ArrayList
     * que se recorre con un for each mostrando por pantalla los atributos de cada objeto Departamento
     */
    public static void mostrarTablaDepartamentos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();

        System.out.println("Numero de Departamento\tUbicación\tJefe\tNombre\ttelefono");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento.getDeptno() + "\t" + departamento.getSede().getUbicacion() + "\t" + departamento.getXefe()!=null?departamento.getXefe().getNome():"" + "\t" +
                    departamento.getNome() + "\t" + departamento.getTelefono());
        }
    }


    /**
     * Este método devuelve una lista con los codigod de los deparatamentos
     * @return ArrayList<>-->idDepartamentos
     * */
    public static ArrayList<Integer> idDepartamentos(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();
        ArrayList<Integer> idDepartamentos=new ArrayList<>();
        for (Departamento departamento : departamentos) {
           idDepartamentos.add(departamento.getDeptno());
        }
       return idDepartamentos;
    }

    /**
     * Este método devuelve una lista con los codigod de los deparatamentos
     * @return ArrayList<>-->idSedes
    */
    public static ArrayList<Integer> idSedes(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede");
        ArrayList<Sede> sedes = (ArrayList<Sede>) query.list();
        ArrayList<Integer> idSedes=new ArrayList<>();
         for (int i= 0;i < sedes.size();i++) {
            idSedes.add(sedes.get(i).getId());
        }

        return idSedes;


    }

    /**
     * este método muestra solo el id y el nombre del departamento.
     * es usado para que el usuario elija en que departamento va a incluir a un nuevo empleado
     * */
    public static void mostrarIdNombreDepartamentos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();

        System.out.println("Código \tNombre");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento.getDeptno()  + "\t\t" +  departamento.getNome());
        }
    }

    /**
     * este método muestra solo el id y la ubicación de la sede.
     * es usado para que el usuario elija en que sede va a incluir a un nuevo departamento
     * */
    public static void mostrarIdUbicacionSedes() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Sede");
        ArrayList<Sede> sedes = (ArrayList<Sede>) query.list();

        System.out.println("Código \tUbicación");
        for (Sede sede : sedes) {
            System.out.println(sede.getId()  + "\t\t" +  sede.getUbicacion());
        }
    }

    /**
     * Muestra los datos de la tabla Contrato
     * usando una query y guardando los datos de la BD en un ArrayList
     * que se recorre con un for each mostrando por pantalla los atributos de cada objeto Contrato
     */
    public static void mostrarTablaContratos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Contrato");
        ArrayList<Contrato> contratos = (ArrayList<Contrato>) query.list();

        System.out.println("id\tEmpregado\tData inicio\tData fin\tSalario\tTipo\tDuración(meses)\tJornada (semanal)");
        for (Contrato contrato : contratos) {
            System.out.println(contrato.getId() + "\t" + contrato.getEmpregado() + "\t" + contrato.getDataInicio() + "\t" + contrato.getDataFin()
                    + "\t" + contrato.getSalario() + "\t" + contrato.getTipo() + "\t" + contrato.getMesesDuracion() + "\t" +
                    contrato.getHorasJornadaSemanal());
        }
    }

  /**
   * Muestra los datos de la tabla empleados
   * TODO: Revisar formato
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

  public static ArrayList<String> arrayDNIs(){
      Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empregado");
      ArrayList<String> dnis = new ArrayList<>();
      ArrayList<Empregado> empregados = (ArrayList<Empregado>) query.list();
      for (int i= 0;i < empregados.size();i++) {
          dnis.add(empregados.get(i).getDNI());
      }
      return dnis;
  }
}