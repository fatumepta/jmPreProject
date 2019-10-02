package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> get(long id) {
        Optional<User> users = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                users = Optional.of(
                        new User(result.getLong("id"),
                                result.getString("name"),
                                result.getString("login"),
                                result.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
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
    public void add(User users) {
        try (PreparedStatement stmt =
                     connection.prepareStatement("INSERT INTO users (name, login, password) VALUES (?, ?, ?)")) {
            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLogin());
            stmt.setString(3, users.getPassword());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User users, String[] params) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE users SET name=?, login=?, password=? WHERE id=?")) {
            stmt.setString(1, params[0]);   // name
            stmt.setString(2, params[1]);   // login
            stmt.setString(3, params[2]);   // password
            stmt.setLong(4, users.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User users) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            stmt.setLong(1, users.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE if NOT EXISTS users (" +
                    "id BIGSERIAL PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL, " +
                    "login VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(50) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE if EXISTS users")) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
