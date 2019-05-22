package Models.Entities;

import Controller.MainController;
import Models.DatabaseDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    private int userId;
    private String userType;
    private String firstName;
    private String lastName;
    private String personalId;
    private String username;
    private String password;
    public static boolean loggedIn = false;


    public UserModel(int userId, String userType, String firstName, String lastName, String personalId, String username, String password) {
        this.userId = userId;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.username = username;
        this.password = password;
    }




    public UserModel() {

    }

    public void populateuser(){
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Match(username) Against(?)");
            statement.setString(1, username);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            while (resultSet.next()) {
                this.userId = resultSet.getInt("userId");
                this.userType = resultSet.getString("userType");
                this.firstName = resultSet.getString("fName");
                this.lastName = resultSet.getString("lName");
                this.personalId = resultSet.getString("personalId");
                this.username = resultSet.getString("username");
                this.password = resultSet.getString("password");
            }
            MainController.loggedIn = true;


            //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();

        }

        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }
}
