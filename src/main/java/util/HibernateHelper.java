package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory == null ? sessionFactory = createSessionFactory() : sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        return sessionFactory = new Configuration().configure().buildSessionFactory();
    }
}
