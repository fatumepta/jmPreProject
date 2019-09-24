package services;

import dao.UserDao;
import models.User;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;


public class UserService {
    private static Connection connection = DBConnection.getConnection();

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

    public void createTable() {
        getUserDao().createTable();
    }

    public void dropTable() {
        getUserDao().dropTable();
    }

}
