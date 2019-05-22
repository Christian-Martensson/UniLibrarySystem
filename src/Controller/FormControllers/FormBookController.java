package Controller.FormControllers;

import Models.Entities.BookModel;
import Models.Entities.UserModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormBookController {
    FormView view;
    UserModel userModel;


    public FormBookController(FormView formView) {
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

            BookModel book = new BookModel(isbn, articleType, title, publisher, publicationYear, genre, author);
            book.loadBookToDb();
            System.out.println(book.toString());

        }
    }
}
