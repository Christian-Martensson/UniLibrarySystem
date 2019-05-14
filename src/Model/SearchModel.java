package Model;

import javax.swing.*;
import java.util.ArrayList;

public class SearchModel {
    private ArrayList<BookModel> listOfBooks;

    public SearchModel() {
    }

    public void searchInDatabase(String searchWord) {
        DatabaseDriver dbDriver = new DatabaseDriver();

        listOfBooks = dbDriver.searchBookFor(searchWord);
        printContent();


    }

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
}
