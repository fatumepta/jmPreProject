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
    // REFACTOR THIS!
    // CHECK IF USER IN DB!
    public void addUser(User user) {
        getUserDao().add(user);
    }

    public void createTable() {
        getUserDao().createTable();
    }

    public void dropTable() {
        getUserDao().dropTable();
    }

}
