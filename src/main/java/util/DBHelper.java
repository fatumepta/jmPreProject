package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


class DBHelper {
    private static Connection connection;
    private static SessionFactory sessionFactory;


    static Connection getConnection() {
        return connection == null ? connection = createConnection() : connection;
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory == null ? sessionFactory = createSessionFactory() : sessionFactory;
    }

    private static Connection createConnection() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();

        try (InputStream fis = loader.getResourceAsStream("jdbc.properties")) {
            properties.load(fis);

            Class.forName(properties.getProperty("db.driver.class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db.conn.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static SessionFactory createSessionFactory() {
        return sessionFactory = new Configuration().configure().buildSessionFactory();
    }
}
