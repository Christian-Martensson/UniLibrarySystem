package Models.Entities;

import Models.DatabaseDriver;
import UI.Views.ErrorMessageView;

import java.sql.*;
import java.util.Date;

public class MagazineModel extends Article {
    private int magazineId;
    private int magazineNr;
    private String publisher;
    private String publicationDate;

    public MagazineModel(int magazineId, int magazineNr, String publisher, String publicationDate, String title, String genre) {
        this.magazineId = magazineId;
        this.magazineNr = magazineNr;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        super.title = title;
        super.genre = genre;
    }

    @Override
    public void insertIntoDb() {
        Connection connection = null;
        System.out.println(this.publicationDate);

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Magazine (title, magazineNr, publisher, genre , publicationDate) " +
                            "VALUES (?, ?, ?, ?, ?); ");
            statement.setString(1, this.title);
            statement.setInt(2, this.magazineNr);
            statement.setString(3, this.publisher);
            statement.setString(4, this.genre);
            statement.setString(5, this.publicationDate);


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
                        "INSERT INTO Barcode (magazineId, goneMissing, isAvailable) " +
                                "VALUES (?, ?, ?);");
                statement.setInt(1, this.magazineId);
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
                    "DELETE FROM Magazine WHERE magazineId = ?;" );
            statement.setInt(1, this.magazineId);

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
                    "UPDATE Magazine SET  title = ?, magazineNr = ?, publisher = ?, genre = ?, publicationDate = ?" +
                            "WHERE magazineId = ?;");
            statement.setString(1, this.title);
            statement.setInt(2, this.magazineNr);
            statement.setString(3, this.publisher);
            statement.setString(4, this.genre);
            statement.setString(5, this.publicationDate);
            statement.setInt(6, this.magazineId);

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
        int magazineId = this.getMagazineId();


        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery = "SELECT * FROM Magazine\n" +
                    "WHERE magazineId = ?\n;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, magazineId);

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
        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
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
            String sqlQuery = "SELECT * FROM Magazine\n" +
                    "WHERE magazineId = ?\n" +
                    "AND available = 1; ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, this.magazineId);

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

    public int getMagazineId() {
        return magazineId;
    }

    public int getMagazineNr() {
        return magazineNr;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
