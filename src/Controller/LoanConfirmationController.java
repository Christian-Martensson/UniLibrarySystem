package Controller;

import Models.Entities.BookModel;
import UI.Views.LoanConfirmationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanConfirmationController {
    private LoanConfirmationView view;
    private BookModel book;

    public LoanConfirmationController(LoanConfirmationView view, BookModel book) {
        this.view = view;
        this.book = book;
        this.view.addConfirmationButtonListener(new ConfirmationButtonListener());
    }

    class ConfirmationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Do loan stuff
            view.dispose();
        }
    }
}



