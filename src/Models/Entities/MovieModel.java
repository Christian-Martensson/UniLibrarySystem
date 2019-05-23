package Models.Entities;

import Models.DatabaseDriver;
import UI.Views.ErrorMessageView;

import java.sql.*;

public class MovieModel extends Article {

    private int movieId;
    private int minimumAge;


    public MovieModel(int movieId, int minimumAge,
                      String title, String publicationYear, String genre,
                      String creator) {
        this.movieId = movieId;
        this.minimumAge = minimumAge;
        super.title = title;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = creator;
    }




    @Override
    public void insertIntoDb() {
        Connection connection = null;
        System.out.println(this.toString());

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery =
                    "INSERT INTO Movie (title, genre, publicationYear , minimumAge, creator) " +
                    "VALUES (?, ?, ?, ?, ?); ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, this.title);
            statement.setString(2, this.genre);
            statement.setString(3, this.publicationYear);
            statement.setInt(4, minimumAge);
            statement.setString(5, this.creator);



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
    public void addBarcodesInDb(int number) {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();
            for(int i = 0; i < number; i++) {
                // 2. Create a statement
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Barcode (movieId, goneMissing, isAvailable) " +
                                "VALUES (?, ?, ?);");
                statement.setInt(1, this.movieId);
                statement.setInt(2, 1);
                statement.setInt(3, 1);

                // 3. Execute SQL query
                statement.executeUpdate();
            }


            //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessageView error = new ErrorMessageView("Error!");
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
    public void removeFromDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Movie WHERE movieId = ?;" );
            statement.setInt(1, this.movieId);

            // 3. Execute SQL query
            statement.executeUpdate();


            //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessageView error = new ErrorMessageView("Error!");
        }

        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void updateInDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Movie SET title = ?, genre = ?, publicationYear = ?, minimumAge = ?, creator = ?" +
                            "WHERE movieId = ?;");
            statement.setString(1, this.title);
            statement.setString(2, this.genre);
            statement.setString(3, this.publicationYear);
            statement.setInt(4, this.minimumAge);
            statement.setString(5, this.creator);
            statement.setInt(6, this.movieId);


            // 3. Execute SQL query
            statement.executeUpdate();


            //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessageView error = new ErrorMessageView("Error!");
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
            String sqlQuery = "CALL spCreateNewLoanMovie(?, ?)";
            CallableStatement statement = connection.prepareCall(sqlQuery);
            statement.setInt(1, barcodeId);
            statement.setInt(2, userId);
            // 3. Execute SQL query
            statement.executeQuery();
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
        System.out.println(barcodeId);
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
            String sqlQuery = "SELECT * FROM Barcode\n" +
                    "WHERE movieId = ?\n" +
                    "AND isAvailable = 1; ";
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
        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
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
