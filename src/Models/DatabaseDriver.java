package Models;

import java.sql.*;


public class DatabaseDriver {
    private final String DATABASE_URL = "jdbc:mysql://localhost:8889/UniLibrarySystem?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "root";

    public DatabaseDriver() {

    }

    public Connection createConnection() {
        try {
            Connection myConn = DriverManager.getConnection(DATABASE_URL, user, password);
            return myConn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
