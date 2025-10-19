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
            try (InputStream in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("application.properties")) {
                props.load(in);
            }
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.driver_class", props.getProperty("spring.datasource.driver-class-name"));
            cfg.setProperty("hibernate.connection.url", props.getProperty("spring.datasource.url"));
            cfg.setProperty("hibernate.connection.username", props.getProperty("spring.datasource.username"));
            cfg.setProperty("hibernate.connection.password", props.getProperty("spring.datasource.password"));
            cfg.setProperty("hibernate.dialect", props.getProperty("spring.jpa.properties.hibernate.dialect"));
            cfg.setProperty("hibernate.hbm2ddl.auto", props.getProperty("spring.jpa.hibernate.ddl-auto"));
            cfg.setProperty("hibernate.show_sql", props.getProperty("spring.jpa.show-sql"));
            cfg.setProperty("hibernate.format_sql", props.getProperty("spring.jpa.properties.hibernate.format_sql"));
            cfg.addAnnotatedClass(ma.projet.beans.Personne.class);
            cfg.addAnnotatedClass(ma.projet.beans.Homme.class);
            cfg.addAnnotatedClass(ma.projet.beans.Femme.class);
            cfg.addAnnotatedClass(ma.projet.beans.Mariage.class);
            sessionFactory = cfg.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
