package services;

import dao.Dao;
import dao.UserDao;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJDBCImpl;
import models.User;
import org.hibernate.SessionFactory;
import util.HibernateHelper;
import util.JDBCHelper;

import java.sql.Connection;
import java.util.List;


public class UserService {
    private Connection connection;
    private SessionFactory sessionFactory;
    private static UserService userService;

    // JDBC
    private UserService(Connection connection) {
        this.connection = connection;
    }

    // JDBC
    public static UserService getInstance() {
        return userService == null ? userService = new UserService(JDBCHelper.getConnection()) : userService;
    }

    // JDBC
    private static UserDao getUserDao() {
        return new UserDaoJDBCImpl(userService.connection);
    }

//    // Hibernate
//    private UserService(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    // Hibernate
//    public static UserService getInstance() {
//        return userService == null ? userService = new UserService(HibernateHelper.getSessionFactory()) : userService;
//    }
//
//    // Hibernate
//    private static UserDaoHibernateImpl getUserDao() {
//        return new UserDaoHibernateImpl(userService.sessionFactory.openSession());
//    }

    public List<User> getAllUsers() {
        return getUserDao().getAll();
    }

    public boolean addUser(String name, String login, String password) {
        Dao<User> dao = getUserDao();
        if (dao.getAll().stream().anyMatch(user -> user.getLogin().equals(login))) {
            return false;
        } else {
            dao.add(new User(name, login, password));
            return true;
        }
    }

    public void editUser(User user, String[] params) {
        getUserDao().update(user, params);
    }

    public void deleteUser(long id) {
        Dao<User> dao = getUserDao();
        dao.get(id).ifPresent(dao::delete);
    }

    public User getUser(long id) {
        Dao<User> dao = getUserDao();
        return dao.get(id).orElse(new User());
    }

    public void createTable() {
        getUserDao().createTable();
    }

    public void dropTable() {
        getUserDao().dropTable();
    }
}
