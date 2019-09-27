package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCHelper {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/preproject";
    private static final String USER = "fatumepta";
    private static final String PASSWORD = "qwerty";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}