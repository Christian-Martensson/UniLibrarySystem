package Controller;

import Models.Entities.Article;
import Models.Entities.LoanModel;
import UI.Views.MessageView;
import UI.Views.LoanConfirmationView;
import UI.Views.LoanReceiptView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                LoanReceiptView view = new LoanReceiptView(article, generateText(barcodeId));
            }

            else {
                MessageView error = new MessageView("This book is not available");
            }

            view.dispose();
        }
    }
    public String generateText(int barcodeId) {
        String text;
        LoanModel loan = LoanModel.fetchLoansFromDbFor(barcodeId);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        text = "-- RECEIPT LOAN --\n" +
                "\nDate: " +  dateFormat.format(date) +
                "\nTitle: " + article.getTitle() +
                "\nLoan ID: " + loan.getLoanId() +
                "\nDue date: " + loan.getDueDate().toString() +
                "\n -- WELCOME BACK! --";

        return text;
    }


}



