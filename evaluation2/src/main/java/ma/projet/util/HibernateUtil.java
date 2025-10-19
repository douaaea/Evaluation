package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties props = new Properties();
            // Lecture du fichier application.properties
            try (InputStream in = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (in == null) {
                    throw new RuntimeException("Impossible de trouver application.properties");
                }
                props.load(in);
            }

            // Configuration Hibernate
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.driver_class", props.getProperty("spring.datasource.driver-class-name"));
            cfg.setProperty("hibernate.connection.url", props.getProperty("spring.datasource.url"));
            cfg.setProperty("hibernate.connection.username", props.getProperty("spring.datasource.username"));
            cfg.setProperty("hibernate.connection.password", props.getProperty("spring.datasource.password"));
            cfg.setProperty("hibernate.dialect", props.getProperty("spring.jpa.properties.hibernate.dialect"));
            cfg.setProperty("hibernate.hbm2ddl.auto", props.getProperty("spring.jpa.hibernate.ddl-auto"));
            cfg.setProperty("hibernate.show_sql", props.getProperty("spring.jpa.show-sql"));
            cfg.setProperty("hibernate.format_sql", props.getProperty("spring.jpa.properties.hibernate.format_sql"));

            // Ajouter les entités de ton projet
            cfg.addAnnotatedClass(ma.projet.classes.Employe.class);
            cfg.addAnnotatedClass(ma.projet.classes.Projet.class);
            cfg.addAnnotatedClass(ma.projet.classes.Tache.class);
            cfg.addAnnotatedClass(ma.projet.classes.EmployeTache.class);

            sessionFactory = cfg.buildSessionFactory();

        } catch (Exception ex) {
            System.err.println("Erreur de configuration Hibernate : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
