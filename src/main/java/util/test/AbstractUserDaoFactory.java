package util.test;

import dao.UserDao;

import java.util.Properties;

public interface AbstractUserDaoFactory {
    UserDao createUserDaoJDBC(Properties properties);

    UserDao createUserDaoHibernate(Properties properties);
}
