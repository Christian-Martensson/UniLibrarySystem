package Models.Entities;

import Models.DatabaseDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookModel extends Article {
    private String isbn;
    private String publisher;

    public BookModel(String isbn, String articleType, String title, String publisher, String publicationYear, String genre, String author) {
        this.isbn = isbn;
        super.articleType = articleType;
        super.title = title;
        this.publisher = publisher;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = author;
    }


    public void loadBookToDb() {
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Book (isbn, articleType, title, publisher , publicationYear, genre) " +
                            "VALUES (?, ?, ?, ?, ?, ?); ");
            statement.setString(1, this.isbn);
            statement.setString(2, this.articleType);
            statement.setString(3, this.title);
            statement.setString(4, this.publisher);
            statement.setString(5, this.publicationYear);
            statement.setString(6, this.genre);

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

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

}
