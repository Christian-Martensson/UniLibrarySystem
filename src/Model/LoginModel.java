package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    private String username, password;
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


    /////////////** OBS!!! CREATE DATABASE CONNECTION! **/////////////////
    ///ONLY EXAMPEL!!////
    public void getCredentials(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection("jdbc:derby:db/medit");
            PreparedStatement select = conn.prepareStatement("SELECT * FROM user_credentials WHERE username = ?");
            select.setString(1, username);
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                password = rs.getString("password");
            }
            else{
                password = "";
            }

        //Catch exceptions
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();

        }catch (SQLException exception) {
            exception.printStackTrace();
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

