package Controller;

import Models.Entities.BookModel;
import Models.Entities.LoanModel;
import UI.Views.ErrorMessageView;
import UI.Views.LoanConfirmationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            // check for availability in db.
            if (true) {
                LoanModel.generateLoan(book, MainController.loggedInUser);
                ArrayList<LoanModel> listOfLoans = LoanModel.fetchLoansFromDBfor(MainController.loggedInUser);
                for (LoanModel loan : listOfLoans) {
                    System.out.println(loan.toString());
                }
            }
            else {
                ErrorMessageView error = new ErrorMessageView("This book is not available");
            }

            view.dispose();
        }
    }
}



