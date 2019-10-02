package util.test;

import dao.UserDao;

import java.util.Properties;

public class UserDaoJDBCImplementationFactory implements AbstractUserDaoFactory {
    @Override
    public UserDao createUserDaoJDBC(Properties properties) {
        return null;
    }

    @Override
    public UserDao createUserDaoHibernate(Properties properties) {
        return null;
    }
}
