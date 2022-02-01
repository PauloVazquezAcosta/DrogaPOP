package drogapop;

import drogapop.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.Properties;

public class HibernateUtil {

  private static SessionFactory sessionFactory;
  private static Session session;
	
  /**
   * Crea la factoria de sesiones, que se utilizará para crear nuevas sesiones
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
    properties.put(Environment.HBM2DDL_AUTO, "create-drop");

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
   * @version 0.0.1
   */
  public static void openSession() {
    session = sessionFactory.openSession();
  }
	
  /**
   * Devuelve la sesión actual. Si no hay una activa, se abre
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
   * @param object -> objeto a añadir a la base de datos
   * @version 0.0.1
   */
  public static void addObject(Object object){
    Session session = HibernateUtil.getCurrentSession();
    session.beginTransaction();
    session.save(object);
    session.getTransaction().commit();
    session.close();
  }

  /**
   * Eliminar elemento de la base de datos
   * @param object -> objeto a eliminar
   * @version 0.0.1
   */
  public static void removeObject(Object object){
    Session session = HibernateUtil.getCurrentSession();
    session.beginTransaction();
    session.delete(object);
    session.getTransaction().commit();
    session.close();
  }

  public static void executeQuery() {
    // TODO: finish code
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM empregados");
    ArrayList<Empregado> empleados = (ArrayList<Empregado>) query.list();
    for (int i = 0; i < empleados.size(); i++)
      System.out.print(empleados.get(i) + " ");
  }
}