package Models.Entities;

import Models.DatabaseDriver;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanModel {
    int loanId; // generated in database
    int userId;
    int barcodeId;
    String dateOfLoan;
    Date dateOfReturn;
    Date dueDate;
    int nrRenewals;

    public void loadLoanToDB() {
        // do db stuff
    }

    public static void generateLoan(BookModel book, UserModel user) {
        int barcodeId = getAvailableBarcodeFor(book.getIsbn());

        createLoanOn(barcodeId, user.getUserId());

    }

    public static void createLoanOn(int barcodeId, int userId) {
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
            System.out.println("I got here!");
        }


        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getAvailableBarcodeFor(String isbn) {
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
                    "AND available = 1; ";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, isbn);

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
        System.out.println(barcode);
        return barcode;
    }
}
