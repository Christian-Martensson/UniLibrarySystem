package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    private String username;
    private String password;
    private Connection conn;

    //Appropriate data is fetched from the database according to controller's cal
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void getCredentials(){
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            this.conn = driver.createConnection();

            // 2. Create a statement
            // statement = myConn.prepareStatement("select password from User where username = ?");
            // "SELECT password FROM User WHERE Match(username) Against(?)"
            PreparedStatement statement = this.conn.prepareStatement("SELECT password FROM User WHERE Match(username) Against(?)");

            statement.setString(1, username);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            if(resultSet.next()){
                password = resultSet.getString("password");
            }
            else{
                password = "";
            }

        //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();

        }

        finally{
            try {
                conn.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}

