package drogapop;

import drogapop.entity.Department;
import drogapop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static SessionFactory sessionFactory;
  private static Session session;
	
  /**
   * Crea la factoria de sesiones
   */
  public static void buildSessionFactory() {

    Configuration configuration = new Configuration();
    configuration.configure();
    // Se registran las clases que hay que mapear con cada tabla de la base de datos
    configuration.addAnnotatedClass(Department.class);
    configuration.addAnnotatedClass(Employee.class);
    
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
      configuration.getProperties()).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }
	
  /**
   * Abre una nueva sesión
   */
  public static void openSession() {
    session = sessionFactory.openSession();
  }
	
  /**
   * Devuelve la sesión actual
   * @return
   */
  public static Session getCurrentSession() {
    if ((session == null) || (!session.isOpen()))
      openSession();
			
    return session;
  }
	
  /**
   * Cierra Hibernate
   */
  public static void closeSessionFactory() {
	
    if (session != null)
      session.close();
		
    if (sessionFactory != null)
      sessionFactory.close();
  }

  // Metodos para tabla empleados:

  // Introducir elemento en la tabla de empleados
  public void añadirObjetoDePrueba(){
    Department departamento = new Department(1, "xd", 1, "Coruña");
    Session sesion = HibernateUtil.getCurrentSession();
    sesion.beginTransaction();
    sesion.save(departamento);
    sesion.getTransaction().commit();
    sesion.close();
  }

  //Eliminar elemento tabla empleados

  // Buscar forma de eliminar un elemento pasando solo un
  // elemento en lugar de pasar el objeto entero por ejemplo:
  // Pasar como parametro el id y ejecutar una query que elimine
  // el elemento de la base de datos con el id que pasamos como parametro
  public void eliminarObjetoDePrueba(){
    Department departamento = new Department(1, "xd", 1, "Coruña");
    Session sesion = HibernateUtil.getCurrentSession();
    sesion.beginTransaction();
    sesion.delete(departamento);
    sesion.getTransaction().commit();
    sesion.close();
  }

  // Metodos tabla empleados y tabla departamentos
  // Ejecutar querys: SELECT * from Departamentos
  //                  SELECT * from Empregados


}