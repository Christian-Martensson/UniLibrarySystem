package Models;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchModel {
    private ArrayList<BookModel> listOfBooks;

    public JTable displayBooks() {
        String[] columnNames = new String[5];
        Object[][] data = new Object[listOfBooks.size()][5];

        columnNames[0] = "ISBN";
        columnNames[1] = "Title";
        columnNames[2] = "Publisher";
        columnNames[3] = "Publication Year";
        columnNames[4] = "Genre";

        for (int i = 0; i < listOfBooks.size(); i++) {
            String isbn = listOfBooks.get(i).getIsbn();
            String title = listOfBooks.get(i).getTitle();
            String publicationYear = listOfBooks.get(i).getPublicationYear();
            String publisher = listOfBooks.get(i).getPublisher();
            String genre = listOfBooks.get(i).getGenre();

            data[i][0] = isbn;
            data[i][1] = title;
            data[i][2] = publicationYear;
            data[i][3] = publisher;
            data[i][4] = genre;
        }
        return new JTable(data, columnNames);
    }


    public void printContent() {
        System.out.println("Title           Publication Year");

        for (BookModel book : listOfBooks) {
            System.out.printf("%s      %s\n",book.getTitle(), book.getPublicationYear());
        }

    }

    public ArrayList<BookModel> searchBookFor(String searchWord) {
        listOfBooks = new ArrayList<>();

        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT b.*, c.* \n" +
                    "FROM Book b\n" +
                    "JOIN BookAuthor ba on b.isbn = ba.isbn\n" +
                    "JOIN Creator c on c.creatorId = ba.authorId\n" +
                    "WHERE Match(b.title) Against(?)\n" +
                    "OR Match(c.fName) Against(?);\n";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, searchWord);
            statement.setString(2, searchWord);

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

}
