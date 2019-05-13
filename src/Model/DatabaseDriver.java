package Model;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseDriver {
    final String DATABASE_URL = "jdbc:mysql://localhost:8889/UniLibrarySystem?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String user = "root";
    final String password = "root";

    public ArrayList<BookModel> searchBookFor(String searchWord) {
        ArrayList<BookModel> listOfBooks = new ArrayList<>();

        try {
            // 1. Get a connection to the database
            Connection myConn = DriverManager.getConnection(DATABASE_URL, user, password);

            // 2. Create a statement
            // statement = myConn.prepareStatement("select * from Book where title = ?");
            PreparedStatement statement = myConn.prepareStatement("SELECT * FROM Book WHERE Match(title) Against(?)");


            statement.setString(1, searchWord);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String publicationYear = resultSet.getString("publicationYear");

                BookModel book = new BookModel(title, publicationYear);
                listOfBooks.add(book);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBooks;
    }
}
