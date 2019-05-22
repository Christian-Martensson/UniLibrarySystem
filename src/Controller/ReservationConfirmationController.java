package Controller;

import Models.Entities.BookModel;

import UI.Views.ReservationConfirmationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationConfirmationController {
    private ReservationConfirmationView view;
    private BookModel book;

    public ReservationConfirmationController(ReservationConfirmationView view, BookModel book) {
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



