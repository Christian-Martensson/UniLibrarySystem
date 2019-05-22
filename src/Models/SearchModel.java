package Models;

import Models.Entities.BookModel;
import Models.Entities.MagazineModel;
import Models.Entities.UserModel;
import Models.Entities.MovieModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SearchModel {
    private ArrayList<BookModel> listOfBooks;
    private ArrayList<UserModel> listOfUsers;
    private ArrayList<MovieModel> listOfMovies;
    private ArrayList<MagazineModel> listOfMagazines;


    public JTable convertListOfBooksToJTable() {
        String[] columnNames = new String[7];
        Object[][] data = new Object[listOfBooks.size()][7];

        columnNames[0] = "ISBN";
        columnNames[1] = "Title";
        columnNames[2] = "Author";
        columnNames[3] = "Publisher";
        columnNames[4] = "Publication Year";
        columnNames[5] = "Genre";
        columnNames[6] = "Available";

        for (int i = 0; i < listOfBooks.size(); i++) {
            String isbn = listOfBooks.get(i).getIsbn();
            String title = listOfBooks.get(i).getTitle();
            String author = listOfBooks.get(i).getCreator();
            String publisher = listOfBooks.get(i).getPublisher();
            String publicationYear = listOfBooks.get(i).getPublicationYear();
            String genre = listOfBooks.get(i).getGenre();
            boolean available = listOfBooks.get(i).isAvailable();

            data[i][0] = isbn;
            data[i][1] = title;
            data[i][2] = author;
            data[i][3] = publisher;
            data[i][4] = publicationYear;
            data[i][5] = genre;
            data[i][6] = available;
        }
        return new JTable(data, columnNames);
    }

    public JTable convertListOfMagazinesToTable() {
        String[] columnNames = new String[6];
        Object[][] data = new Object[listOfMagazines.size()][6];

        columnNames[0] = "Magazine ID";
        columnNames[1] = "Title";
        columnNames[2] = "Magazine Nr";
        columnNames[3] = "Publisher";
        columnNames[4] = "Publication date";
        columnNames[5] = "Genre";

        for (int i = 0; i < listOfMagazines.size(); i++) {
            int magazineId = listOfMagazines.get(i).getMagazineId();
            String title = listOfMagazines.get(i).getTitle();
            int magazineNr = listOfMagazines.get(i).getMagazineNr();
            String publisher = listOfMagazines.get(i).getPublisher();
            Date publicationDate = listOfMagazines.get(i).getPublicationDate();
            String genre = listOfMagazines.get(i).getGenre();

            data[i][0] = magazineId;
            data[i][1] = title;
            data[i][2] = magazineNr;
            data[i][3] = publisher;
            data[i][4] = publicationDate;
            data[i][5] = genre;
        }
        return new JTable(data, columnNames);
    }

    public JTable convertListOfMoviesToTable() {
        String[] columnNames = new String[7];
        Object[][] data = new Object[listOfMovies.size()][7];

        columnNames[0] = "Movie ID";
        columnNames[1] = "Title";
        columnNames[2] = "Producer";
        columnNames[3] = "Publication Year";
        columnNames[4] = "Genre";
        columnNames[5] = "Minimum Age";
        columnNames[6] = "Available";

        for (int i = 0; i < listOfMovies.size(); i++) {
            int movieId = listOfMovies.get(i).getMovieId();
            String title = listOfMovies.get(i).getTitle();
            String producer = listOfMovies.get(i).getCreator();
            String publicationYear = listOfMovies.get(i).getPublicationYear();
            String genre = listOfMovies.get(i).getGenre();
            int minimumAge = listOfMovies.get(i).getMinimumAge();
            boolean available = listOfMovies.get(i).isAvailable();

            data[i][0] = movieId;
            data[i][1] = title;
            data[i][2] = producer;
            data[i][3] = publicationYear;
            data[i][4] = genre;
            data[i][5] = minimumAge;
            data[i][6] = available;
        }
        return new JTable(data, columnNames);
    }

    public JTable convertListOfUsersToJTable() {
        String[] columnNames = new String[5];
        Object[][] data = new Object[listOfUsers.size()][5];

        columnNames[0] = "Username";
        columnNames[1] = "Personal ID";
        columnNames[2] = "First name";
        columnNames[3] = "Last name";
        columnNames[4] = "User type";

        for (int i = 0; i < listOfUsers.size(); i++) {
            String userType = listOfUsers.get(i).getUserType();
            String firstName = listOfUsers.get(i).getFirstName();
            String lastName = listOfUsers.get(i).getLastName();
            String personalId = listOfUsers.get(i).getPersonalId();
            String username = listOfUsers.get(i).getUsername();


            data[i][0] = username;
            data[i][1] = personalId;
            data[i][2] = firstName;
            data[i][3] = lastName;
            data[i][4] = userType;
        }
        return new JTable(data, columnNames);
    }

    public ArrayList<BookModel> searchBook(String searchWord) {
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
                String author = resultSet.getString("c.fName") + " " + resultSet.getString("c.lName");
                int availability = resultSet.getInt("availability");

                BookModel book = new BookModel(isbn, articleType, title, publisher, publicationYear, genre, author, availability);
                listOfBooks.add(book);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBooks;
    }

    public ArrayList<MovieModel> searchMovie(String searchWord) {
        listOfMovies = new ArrayList<>();

        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT m.*, c.* \n" +
                    "FROM Movie m\n" +
                    "JOIN MovieProducer mp on mp.movieId = m.movieId\n" +
                    "JOIN Creator c on c.creatorId = mp.creatorId\n" +
                    "WHERE Match(m.title) Against(?)\n" +
                    "OR Match(c.fName) Against(?)";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, searchWord);
            statement.setString(2, searchWord);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                int movieId = resultSet.getInt("movieId");
                String articleType = resultSet.getString("articleType");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String publicationYear = resultSet.getString("publicationYear");
                String producer = resultSet.getString("c.fName") + " " + resultSet.getString("c.lName");
                int availability = resultSet.getInt("availability");
                int minimumAge = resultSet.getInt("m.minimumAge");

                MovieModel movie = new MovieModel(movieId, minimumAge, articleType, title, publicationYear, genre, producer, availability);
                listOfMovies.add(movie);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfMovies;
    }

    public ArrayList<MagazineModel> searchMagazine(String searchWord) {
        listOfMagazines = new ArrayList<>();

        Connection connection = null;
        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            // previous: "SELECT * FROM Book WHERE Match(title) Against(?)"
            String sqlQuery = "SELECT *\n" +
                    "FROM Magazine\n" +
                    "WHERE Match(title) Against(?)\n" +
                    "OR Match(genre) Against(?);";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, searchWord);
            statement.setString(2, searchWord);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                int magazineId = resultSet.getInt("magazineId");
                int magazineNr = resultSet.getInt("magazineNr");
                String publisher = resultSet.getString("publisher");
                Date publicationDate = resultSet.getDate("publicationDate");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");


                MagazineModel magazine = new MagazineModel(magazineId, magazineNr, publisher, publicationDate, title, genre);
                listOfMagazines.add(magazine);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfMagazines;
    }

    public void searchUser(String searchWord){
        Connection connection = null;
        listOfUsers = new ArrayList<>();


        try {
            // 1. Get a connection to the database
            DatabaseDriver driver = new DatabaseDriver();
            connection = driver.createConnection();

            // 2. Create a statement
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Match(username) Against(?)");
            statement.setString(1, searchWord);

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            while (resultSet.next()) {
                String userType = resultSet.getString("userType");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");
                String personalId = resultSet.getString("personalId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                UserModel user = new UserModel(userType, firstName, lastName, personalId, username, password);

                listOfUsers.add(user);
            }

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

    public ArrayList<BookModel> getListOfBooks() {
        return listOfBooks;
    }

    public ArrayList<UserModel> getListOfUsers() {
        return listOfUsers;
    }

    public BookModel getBookWith(String isbn) {
        for (BookModel book : listOfBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
