package util;

import dao.UserDao;
import dao.UserDaoHibernateImplementation;
import dao.UserDaoJDBCImplementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDao dao;
    private static final Properties properties;

    static {
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream fis = loader.getResourceAsStream("dao.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getDao() {
        String property = properties.getProperty("user.dao");

        if (property.equalsIgnoreCase("hibernate")) {
            dao = new UserDaoHibernateImplementation(DBHelper.getSessionFactory().openSession());
        } else if (property.equalsIgnoreCase("jdbc")) {
            dao = new UserDaoJDBCImplementation(DBHelper.getConnection());
        }

        return dao;
    }
}
