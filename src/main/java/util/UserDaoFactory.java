package util;

import dao.UserDao;
import dao.UserDaoHibernate;
import dao.UserDaoJDBC;
import util.DBHelper;

import java.util.Properties;

public class UserDaoFactory {
    private static UserDao dao;

    public static UserDao getDao(Properties properties) {
        String property = properties.getProperty("user.dao");

        if (property.equalsIgnoreCase("hibernate")) {
            dao = new UserDaoHibernate(DBHelper.getSessionFactory().openSession());
        } else if (property.equalsIgnoreCase("jdbc")) {
            dao = new UserDaoJDBC(DBHelper.getConnection());
        }

        return dao;
    }
}
