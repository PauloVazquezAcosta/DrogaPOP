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

import java.sql.Date;

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
        Empregado miguel = new Empregado(401,"34305264X", "Miguel", "Díaz Hernández",
                1, "Traballador", "622177680", new Date(20-6-2000),
                "mdiaz@fpcoruna.afundacion.org", "123456789");

        HibernateUtil.buildSessionFactory();
        HibernateUtil.addObject(miguel);
    }

    public static void menu(){
        System.out.println("MENU PRINCIPAL");
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
    
}
