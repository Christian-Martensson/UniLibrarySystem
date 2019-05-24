package Models.Entities;

import Models.DatabaseDriver;
import UI.Views.MessageView;

import java.sql.*;
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

    public static LoanModel fetchLoansFromDbFor(int barcode) {
        LoanModel loanModel = null;

        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT * FROM Loan\n" +
                    "WHERE barcodeId = ?\n;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, barcode);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            if (resultSet.next()) {
                int loanId = resultSet.getInt("loanId");
                int userId = resultSet.getInt("userId");
                int barcodeId = resultSet.getInt("barcodeId");
                Date dateOfLoan = resultSet.getDate("dateOfLoan");
                Date dateOfReturn = resultSet.getDate("dateOfReturn");
                Date dueDate = resultSet.getDate("dueDate");
                int nrRenewals = resultSet.getInt("nrRenewals");
                loanModel = new LoanModel(loanId, userId, barcodeId, dateOfLoan, dateOfReturn, dueDate, nrRenewals);
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
        return loanModel;
    }

    public static ArrayList<LoanModel> fetchLoansFromDbFor(UserModel user) {
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

    public static ArrayList<LoanModel> fetchOverdueLoansFromDb() {
        ArrayList<LoanModel> listOfLoans = new ArrayList<>();

        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "  SELECT * FROM Loan\n" +
                    "  WHERE CURDATE() > dueDate\n" +
                    "  AND dateOfReturn IS NULL\n" +
                    "  OR dateOfReturn > dueDate;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

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

    public void removeFromDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Loan WHERE loanId = ?;" );
            statement.setInt(1, this.loanId);

            // 3. Execute SQL query
            statement.executeUpdate();


            //Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            MessageView error = new MessageView("Error!");
        }

        finally{
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void returnLoan() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            String sqlQuery = "CALL spReturnLoan(?)";
            CallableStatement statement = connection.prepareCall(sqlQuery);
            statement.setInt(1, this.loanId);

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

    public int getLoanId() {
        return loanId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBarcodeId() {
        return barcodeId;
    }

    public Date getDateOfLoan() {
        return dateOfLoan;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getNrRenewals() {
        return nrRenewals;
    }

    @Override
    public String toString() {
        return "LoanModel{" +
                "reserverationId=" + loanId +
                ", userId=" + userId +
                ", barcodeId=" + barcodeId +
                ", dateOfReservation=" + dateOfLoan +
                ", dateOfReturn=" + dateOfReturn +
                ", dueDate=" + dueDate +
                ", nrRenewals=" + nrRenewals +
                '}';
    }
}
