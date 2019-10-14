package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImplementation implements UserDao {
    private Connection connection;

    public UserDaoJDBCImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(long id) {
        User user = new User();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public long getId(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT id FROM users WHERE login=? AND password=?")) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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
                        result.getString("password"),
                        result.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public void add(User users) {
        try (PreparedStatement stmt =
                     connection.prepareStatement("INSERT INTO users (name, login, password, role) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLogin());
            stmt.setString(3, users.getPassword());
            stmt.setString(4, users.getRole());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User users, String[] params) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE users SET name=?, login=?, password=?, role=? WHERE id=?")) {
            stmt.setString(1, params[0]);   // name
            stmt.setString(2, params[1]);   // login
            stmt.setString(3, params[2]);   // password
            stmt.setString(4, params[3]);   // role
            stmt.setLong(5, users.getId());

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
                    "password VARCHAR(50) NOT NULL," +
                    "role VARCHAR(5) NOT NULL)");
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
