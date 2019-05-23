package Models.Entities;

import Models.DatabaseDriver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class LoanModel {
    int loanId; // generated in database
    int userId;
    int barcodeId;
    Date dateOfLoan;
    Date dateOfReturn;
    Date dueDate;
    int nrRenewals;


    public LoanModel(int loanId, int userId, int barcodeId, Date dateOfLoan, Date dateOfReturn, Date dueDate, int nrRenewals) {
        this.loanId = loanId;
        this.userId = userId;
        this.barcodeId = barcodeId;
        this.dateOfLoan = dateOfLoan;
        this.dateOfReturn = dateOfReturn;
        this.dueDate = dueDate;
        this.nrRenewals = nrRenewals;
    }

    public static ArrayList<LoanModel> fetchLoansFromDBfor(UserModel user) {
        ArrayList<LoanModel> listOfLoans = new ArrayList<>();

        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT * FROM Loan\n" +
                    "WHERE userId = ?\n;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, user.getUserId());

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            while (resultSet.next()) {
                int loanId = resultSet.getInt("loanId");
                int userId = resultSet.getInt("userId");
                int barcodeId = resultSet.getInt("barcodeId");
                Date dateOfLoan = resultSet.getDate("dateOfLoan");
                Date dateOfReturn = resultSet.getDate("dateOfReturn");
                Date dueDate = resultSet.getDate("dueDate");
                int nrRenewals = resultSet.getInt("nrRenewals");
                LoanModel loanModel = new LoanModel(loanId, userId, barcodeId, dateOfLoan, dateOfReturn, dueDate, nrRenewals);
                listOfLoans.add(loanModel);
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
        return listOfLoans;
    }

    @Override
    public String toString() {
        return "LoanModel{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", barcodeId=" + barcodeId +
                ", dateOfLoan=" + dateOfLoan +
                ", dateOfReturn=" + dateOfReturn +
                ", dueDate=" + dueDate +
                ", nrRenewals=" + nrRenewals +
                '}';
    }
}
