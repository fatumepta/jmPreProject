package services;

import dao.UserDao;
import models.User;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;


public class UserService {
    private static Connection connection = DBConnection.getConnection();
    private static UserService instance;

    private UserService() {
    }

    private static UserDao getUserDao() {
        return new UserDao(connection);
    }

    public List<User> getAllUsers() {
        return getUserDao().getAll();
    }

    public boolean addUser(String name, String login, String password) {
        UserDao dao = getUserDao();
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
        UserDao dao = getUserDao();
        dao.get(id).ifPresent(dao::delete);
    }

    // I DON'T LIKE IT!
    public User getUser(long id) {
        UserDao dao = getUserDao();
        return dao.get(id).orElse(new User());
    }

    public void createTable() {
        getUserDao().createTable();
    }

    public void dropTable() {
        getUserDao().dropTable();
    }

    public static UserService getInstance() {
        return instance == null ? instance = new UserService() : instance;
    }

}
