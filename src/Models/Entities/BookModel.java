package Models.Entities;

import Models.DatabaseDriver;
import UI.Views.ErrorMessageView;

import java.sql.*;

public class BookModel extends Article {
    private String isbn;
    private String publisher;

    public BookModel(String isbn, String articleType, String title, String publisher, String publicationYear, String genre, String creator) {
        this.isbn = isbn;
        super.articleType = articleType;
        super.title = title;
        this.publisher = publisher;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = creator;
    }

    @Override
    public void insertIntoDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Book (isbn, articleType, title, publisher , publicationYear, genre, creator) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?); ");
            statement.setString(1, this.isbn);
            statement.setString(2, this.articleType);
            statement.setString(3, this.title);
            statement.setString(4, this.publisher);
            statement.setString(5, this.publicationYear);
            statement.setString(6, this.genre);
            statement.setString(7, this.creator);

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
    public void removeFromDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Book WHERE isbn = ?;" );
            statement.setString(1, this.isbn);

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
                    "UPDATE Book SET articleType = ?, title = ?, publisher = ?, publicationYear = ?, genre = ?, creator = ?" +
                            "WHERE isbn = ?;");
            statement.setString(1, this.articleType);
            statement.setString(2, this.title);
            statement.setString(3, this.publisher);
            statement.setString(4, this.publicationYear);
            statement.setString(5, this.genre);
            statement.setString(6, this.creator);
            statement.setString(7, this.isbn);


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
        String isbn = this.getIsbn();

        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery = "SELECT * FROM Book\n" +
                    "WHERE isbn = ?\n;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, isbn);

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
            String sqlQuery = "CALL spCreateNewLoanBook(?, ?)";
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
            String sqlQuery = "SELECT * FROM Barcode\n" +
                    "WHERE isbn = ?\n" +
                    "AND isAvailable = 1; ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, this.isbn);

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

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

}
