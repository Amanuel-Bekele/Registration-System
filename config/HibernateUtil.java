package com.waaproject.registrationsystem.config;


import com.waaproject.registrationsystem.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.springframework.context.annotation.Configuration;
import java.util.Properties;
/**
 * Build a SessionFactory Object based on db properties.
 *
 * Just say HibernateUtil.getSessionFactory from anywhere.
 */
@Configuration
public class HibernateUtil {

    private static SessionFactory buildSessionFactory;

    private static SessionFactory buildSessionFactoryMethod() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

            /**
             * Setting Database properties for hibernate
             * Change these properties to your db settings.
             *
             */
            Properties props = new Properties();


            props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://registrationsystem.cctn4agw5hyx.us-east-2.rds.amazonaws.com:3306/registrationsystem?useSSL=false");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "root2018");
            props.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(props);


            // Adding all entity classes.


            configuration.addAnnotatedClass(Block.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Prerequisite.class);
            configuration.addAnnotatedClass(Professor.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Section.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(StudentSection.class);
            configuration.addAnnotatedClass(Preference.class);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (buildSessionFactory == null)
            buildSessionFactory = buildSessionFactoryMethod();
        return buildSessionFactory;
    }

}