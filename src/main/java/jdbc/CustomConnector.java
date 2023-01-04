package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnector {
    public Connection getConnection(String url) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            return null;
        }
    }

    public Connection getConnection(String url, String user, String password)  {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            return null;
        }
    }
}
