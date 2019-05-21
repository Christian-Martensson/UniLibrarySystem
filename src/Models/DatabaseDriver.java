package Models;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseDriver {
    final String DATABASE_URL = "jdbc:mysql://localhost:8889/UniLibrarySystem?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String user = "root";
    final String password = "root";

    public DatabaseDriver() {

    }

    public Connection createConnection() {
        try {
            Connection myConn = DriverManager.getConnection(DATABASE_URL, user, password);
            return myConn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<BookModel> searchBookFor(String searchWord) {
        ArrayList<BookModel> listOfBooks = new ArrayList<>();

        try {

            // 1. Get a connection to the database
            Connection myConn = this.createConnection();

            // 2. Create a statement
            // To search for specific book, use: "select * from Book where title = ?";
            PreparedStatement statement = myConn.prepareStatement("SELECT * FROM Book WHERE Match(title) Against(?)");

            statement.setString(1, searchWord);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String articleType = resultSet.getString("articleType");
                String title = resultSet.getString("title");
                String publisher = resultSet.getString("publisher");
                String genre = resultSet.getString("genre");
                String publicationYear = resultSet.getString("publicationYear");
                // int availability = resultSet.getInt("availability");

                BookModel book = new BookModel(isbn, articleType, title, publisher, genre, publicationYear);
                listOfBooks.add(book);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBooks;
    }

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}