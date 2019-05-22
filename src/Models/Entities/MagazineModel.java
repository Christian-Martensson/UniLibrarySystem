package Models.Entities;

import Models.DatabaseDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MagazineModel extends Article {
    private int magazineId;
    private int magazineNr;
    private String publisher;
    private Date publicationDate;

    public MagazineModel(int magazineId, int magazineNr, String publisher, Date publicationDate, String title, String genre) {
        this.magazineId = magazineId;
        this.magazineNr = magazineNr;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        super.title = title;
        super.genre = genre;
    }

    public void loadMagazineToDb() {
        Connection connection = null;
        java.sql.Date sqlDate = new java.sql.Date(this.publicationDate.getTime());

        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Magazine (title, magazineNr, publisher, genre , publicationDate) " +
                            "VALUES (?, ?, ?, ?, ?); ");
            statement.setString(1, this.title);
            statement.setInt(2, this.magazineNr);
            statement.setString(3, this.publisher);
            statement.setString(4, this.genre);
            statement.setDate(5, sqlDate);


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


    public int getMagazineId() {
        return magazineId;
    }

    public int getMagazineNr() {
        return magazineNr;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }


}
