package Controller.FormControllers;

import Models.Entities.BookModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormBookController {
    private FormView view;
    private BookModel bookModel;
    private final int ADDING = 1;
    private final int EDITING = 2;
    private int option;


    public FormBookController(FormView formView, int option) {
        this.option = option;
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = view.getText(0);
            String title = view.getText(1);
            String articleType = view.getText(2);
            String publisher = view.getText(3);
            String publicationYear = view.getText(4);
            String genre = view.getText(5);
            String author = view.getText(6);
            bookModel = new BookModel(isbn, articleType, title, publisher, publicationYear, genre, author);

            if(option == ADDING) {
                bookModel.insertIntoDb();
                if (bookModel.isLoadSuccessful()) {
                    bookModel.addBarcodesInDb(1, title);
                }
            }

            else if (option == EDITING) {
                bookModel.updateInDb();

            }
        }
    }
}
