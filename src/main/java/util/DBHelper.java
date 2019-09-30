package util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;

// should be Singleton
public class DBHelper {
    public Connection getConnection() {
        return null;
    }

    public Configuration getConfiguraton() {
        return null;
    }
}
