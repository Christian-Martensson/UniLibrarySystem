package Models.Entities;

import Models.DatabaseDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class LoanModel {
    int loanId;
    int userId;
    int barcodeId;
    Date dateOfLoan;
    Date dateOfReturn;
    Date dueDate;
    int nrRenewals;

    public void loadLoanToDB() {
        // do db stuff
    }

    public static void generateLoan(BookModel book, UserModel user) {
        LoanModel loan = new LoanModel();
        loan.barcodeId = getAvailableBarcodeFor(book.getIsbn());

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
            String sqlQuery = "";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, isbn);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            while (resultSet.next()) {

            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return barcode;
    }
}
