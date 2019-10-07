package services;

import dao.UserDao;
import models.User;
import util.UserDaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class UserService {
    private static UserService userService;
    private static final Properties properties;

    private UserDao dao;

    static {
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream fis = loader.getResourceAsStream("dao.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserService(UserDao dao) {
        this.dao = dao;
    }

    public static UserService getInstance() {
        return userService == null
                ? userService = new UserService(UserDaoFactory.getDao(properties))
                : userService;
    }

    public List<User> getAllUsers() {
        return dao.getAll();
    }

    public boolean addUser(String name, String login, String password) {
        if (dao.getAll().stream().anyMatch(user -> user.getLogin().equals(login))) {
            return false;
        } else {
            dao.add(new User(name, login, password));
            return true;
        }
    }

    public void editUser(User user, String[] params) {
        dao.update(user, params);
    }

    public void deleteUser(long id) {
        dao.get(id).ifPresent(dao::delete);
    }

    public User getUser(long id) {
        return dao.get(id).orElse(new User());
    }

    public void createTable() {
        dao.createTable();
    }

    public void dropTable() {
        dao.dropTable();
    }
}
