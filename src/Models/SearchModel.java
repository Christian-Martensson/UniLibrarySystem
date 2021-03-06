package Models;

import Models.Entities.*;

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

    public static JTable converListOfLoansToTable(ArrayList<LoanModel> listOfLoans) {
        String[] columnNames = new String[6];
        Object[][] data = new Object[listOfLoans.size()][6];

        columnNames[0] = "Loan ID";
        columnNames[1] = "User ID";
        columnNames[2] = "Barcode ID";
        columnNames[3] = "Date of loan";
        columnNames[4] = "Due date";
        columnNames[5] = "Date returned";


        for (int i = 0; i < listOfLoans.size(); i++) {
            int loanId = listOfLoans.get(i).getLoanId();
            int userId = listOfLoans.get(i).getUserId();
            int barcodeId = listOfLoans.get(i).getBarcodeId();
            Date dateOfLoan = listOfLoans.get(i).getDateOfLoan();
            Date dueDate = listOfLoans.get(i).getDueDate();
            Date dateOfReturn = listOfLoans.get(i).getDateOfReturn();

            data[i][0] = loanId;
            data[i][1] = userId;
            data[i][2] = barcodeId;
            data[i][3] = dateOfLoan;
            data[i][4] = dueDate;
            data[i][5] = dateOfReturn;
        }
        return new JTable(data, columnNames);
    }

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
            // fix
            boolean available = listOfBooks.get(i).checkAvailabilityInDb();

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
            String publicationDate = listOfMagazines.get(i).getPublicationDate();
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
            // fix
            boolean available = true;

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
        String[] columnNames = new String[6];
        Object[][] data = new Object[listOfUsers.size()][6];

        columnNames[0] = "User ID";
        columnNames[1] = "Username";
        columnNames[2] = "Personal ID";
        columnNames[3] = "First name";
        columnNames[4] = "Last name";
        columnNames[5] = "User type";

        for (int i = 0; i < listOfUsers.size(); i++) {
            int userId = listOfUsers.get(i).getUserId();
            String userType = listOfUsers.get(i).getUserType();
            String firstName = listOfUsers.get(i).getFirstName();
            String lastName = listOfUsers.get(i).getLastName();
            String personalId = listOfUsers.get(i).getPersonalId();
            String username = listOfUsers.get(i).getUsername();

            data[i][0] = userId;
            data[i][1] = username;
            data[i][2] = personalId;
            data[i][3] = firstName;
            data[i][4] = lastName;
            data[i][5] = userType;
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
            String sqlQuery;
            PreparedStatement statement = null;

            if (searchWord.length() == 0) {
                sqlQuery = "SELECT * FROM Book;";

                statement = connection.prepareStatement(sqlQuery);
            }
            else {
                 sqlQuery = "SELECT * \n" +
                        "FROM Book\n" +
                        "WHERE Match(title) Against(?)\n" +
                        "OR Match(creator) Against(?)\n" +
                        "OR Match(genre) Against(?)\n" +
                        "OR Match(isbn) Against(?)\n;";

                statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, searchWord);
                statement.setString(2, searchWord);
                statement.setString(3, searchWord);
                statement.setString(4, searchWord);
            }

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
                String creator = resultSet.getString("creator");

                BookModel book = new BookModel(isbn, articleType, title, publisher, publicationYear, genre, creator);
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
            String sqlQuery;
            PreparedStatement statement = null;
            if (searchWord.length() == 0) {
                sqlQuery = "SELECT * FROM Movie;";
                statement = connection.prepareStatement(sqlQuery);
            }
            else {
                sqlQuery = "SELECT * FROM Movie\n" +
                        "WHERE Match(title) Against(?)\n" +
                        "OR Match(creator) Against(?)" +
                        "OR Match(genre) Against(?)";
                statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, searchWord);
                statement.setString(2, searchWord);
                statement.setString(3, searchWord);
            }

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                int movieId = resultSet.getInt("movieId");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String publicationYear = resultSet.getString("publicationYear");
                String creator = resultSet.getString("creator");
                int availability = resultSet.getInt("isAvailable");
                int minimumAge = resultSet.getInt("minimumAge");

                MovieModel movie = new MovieModel(movieId, minimumAge, title, publicationYear, genre, creator);
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
            String sqlQuery;
            PreparedStatement statement = null;
            if (searchWord.length() == 0) {
                sqlQuery = "SELECT *\n" +
                        "FROM Magazine;";
                statement = connection.prepareStatement(sqlQuery);
            }

            else {
                sqlQuery = "SELECT *\n" +
                        "FROM Magazine\n" +
                        "WHERE Match(title) Against(?)\n" +
                        "OR Match(genre) Against(?);";
                statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, searchWord);
                statement.setString(2, searchWord);
            }
            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();


            // 4. Process the result set
            while (resultSet.next()) {
                int magazineId = resultSet.getInt("magazineId");
                int magazineNr = resultSet.getInt("magazineNr");
                String publisher = resultSet.getString("publisher");
                String publicationDate = resultSet.getString("publicationDate");
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
            PreparedStatement statement = null;
            String sqlQuery;
            if(searchWord.length() == 0) {
                sqlQuery = "SELECT * FROM User";
                statement = connection.prepareStatement(sqlQuery);
            } else {
                sqlQuery = "SELECT * FROM User WHERE username = ?";
                statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, searchWord);
            }

            // 3. Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // 4. Process the result set
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userType = resultSet.getString("userType");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");
                String personalId = resultSet.getString("personalId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                UserModel user = new UserModel(userId, userType, firstName, lastName, personalId, username, password);

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

    public MovieModel getMovieWith(int value) {
        for (MovieModel movie : listOfMovies) {
            if (movie.getMovieId() == value) {
                return movie;
            }
        }
        return null;
    }

    public MagazineModel getMagazineWith(int value) {
        for (MagazineModel magazine : listOfMagazines) {
            if (magazine.getMagazineId() == value) {
                return magazine;
            }
        }
        return null;
    }

    public UserModel getUserWith(int value) {
        for (UserModel userModel : listOfUsers) {
            if (userModel.getUserId() == value) {
                return userModel;
            }
        }
        return null;
    }


}
