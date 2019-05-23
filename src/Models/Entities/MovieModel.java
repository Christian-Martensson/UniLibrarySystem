package Models.Entities;

import Controller.MainController;
import Models.DatabaseDriver;

import java.sql.*;

public class MovieModel extends Article implements DatabaseActions {

    private int movieId;
    private int minimumAge;


    public MovieModel(int movieId, int minimumAge, String articleType,
                      String title, String publicationYear, String genre,
                      String producer) {
        this.movieId = movieId;
        this.minimumAge = minimumAge;
        super.articleType = articleType;
        super.title = title;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = producer;
    }




    @Override
    public void loadToDb() {
        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Movie (articleType, title, genre, publicationYear , minimumAge) " +
                            "VALUES (?, ?, ?, ?, ?); ");
            statement.setString(1, this.articleType);
            statement.setString(2, this.title);
            statement.setString(3, this.genre);
            statement.setString(4, this.publicationYear);
            statement.setInt(5, minimumAge);


            // 3. Execute SQL query
            statement.executeUpdate();


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

    @Override
    public boolean checkAvailabilityInDb() {
        boolean isAvailable = false;
        int movieId = this.getMovieId();

        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery = "SELECT * FROM Movie\n" +
                    "WHERE movieId = ?\n;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, movieId);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            if (resultSet.next()) {
                int isAvailableInt = resultSet.getInt("isAvailable");
                if (isAvailableInt == 1){
                    isAvailable = true;
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return isAvailable;

    }

    @Override
    public void createLoan(int barcodeId, int userId) {

        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery = "CALL spCreateNewLoan(?, ?)";
            CallableStatement statement = connection.prepareCall(sqlQuery);
            statement.setInt(1, barcodeId);
            statement.setInt(2, userId);
            // 3. Execute SQL query
            statement.executeQuery();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAvailableBarcode() {
        // move to book, magazine and movie
        int barcode = 0;

        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT * FROM Movie\n" +
                    "WHERE movieId = ?\n" +
                    "AND available = 1; ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, this.movieId);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            if (resultSet.next()) {
                barcode = resultSet.getInt("barcodeId");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return barcode;
    }


    public int getMovieId() {
        return movieId;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}
