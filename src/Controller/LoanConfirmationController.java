package Controller;

import Models.Entities.Article;
import Models.Entities.LoanModel;
import UI.Views.ErrorMessageView;
import UI.Views.LoanConfirmationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanConfirmationController {
    private LoanConfirmationView view;
    private Article article;

    public LoanConfirmationController(LoanConfirmationView view, Article article) {
        this.view = view;
        this.article = article;
        this.view.addConfirmationButtonListener(new ConfirmationButtonListener());
    }

    class ConfirmationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // check for availability in db.
            boolean isAvailable = article.checkAvailabilityInDb();

            if (isAvailable) {
                int userId = MainController.loggedInUser.getUserId();
                int barcodeId = article.getAvailableBarcode();
                article.createLoan(barcodeId, userId);
            }

            else {
                ErrorMessageView error = new ErrorMessageView("This book is not available");
            }

            view.dispose();
        }
    }
}



