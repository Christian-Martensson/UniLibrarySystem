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
        String[] columnNames = new String[2];
        Object[][] data = new Object[listOfBooks.size()][2];

        columnNames[0] = "Title";
        columnNames[1] = "Publication year";

        for (int i = 0; i < listOfBooks.size(); i++) {
            String title = listOfBooks.get(i).getTitle();
            String publicationYear = listOfBooks.get(i).getPublicationYear();
            data[i][0] = title;
            data[i][1] = publicationYear;
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
