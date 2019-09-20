package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                allUsers.add(new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public void add(User user) {
        try (PreparedStatement stmt =
                     connection.prepareStatement("INSERT INTO users (name, login, password) VALUES (?, ?, ?)")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, String[] params) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE users SET name=? login=? password=? WHERE id=?")) {
            stmt.setString(1, params[0]);   // name
            stmt.setString(2, params[1]);   // login
            stmt.setString(3, params[2]);   // password
            stmt.setLong(4, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            stmt.setLong(1, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE if NOT EXISTS users (id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL, login VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE if EXISTS users")) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
