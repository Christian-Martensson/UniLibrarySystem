package Models.Entities;

import Models.DatabaseDriver;

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
        LoanModel loan = new LoanModel();
        loan.barcodeId = getAvailableBarcodeFor(book.getIsbn());
        //loan.userId = user.getUserId();


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        loan.dateOfLoan = date;



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
        return barcode;
    }
}
