package Models.Entities;

import Models.DatabaseDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieModel extends Article {

    private int movieId;
    private int minimumAge;


    public MovieModel(int movieId, int minimumAge, String articleType,
                      String title, String publicationYear, String genre,
                      String producer) {
        this.movieId = movieId;
        this.minimumAge = minimumAge;
        super.articleType = articleType;
        super.title = title;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = producer;
    }

    public void loadMovieToDb() {
        Connection connection = null;

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Movie (articleType, title, genre, publicationYear , minimumAge) " +
                            "VALUES (?, ?, ?, ?, ?); ");
            statement.setString(1, this.articleType);
            statement.setString(2, this.title);
            statement.setString(3, this.genre);
            statement.setString(4, this.publicationYear);
            statement.setInt(5, minimumAge);


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

    public int getMovieId() {
        return movieId;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}
