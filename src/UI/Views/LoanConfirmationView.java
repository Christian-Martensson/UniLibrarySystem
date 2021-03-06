package UI.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Models.Entities.Article;
import Models.Entities.BookModel;
import Models.Entities.MovieModel;

public class LoanConfirmationView extends JFrame {

    private JLabel labelTitle;
    private JLabel labelIsbn;
    private JButton confirmationButton;
    private Article article;
    private Container contentPane;

    public LoanConfirmationView(Article article) {
        this.article = article;
        buildFrame();
    }

    public void buildFrame() {
        //Create Loan Confirmation View
        setTitle("Loan confirmation");
        setSize(400,250);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //Create Title Label
        labelTitle = new JLabel("Title: " + article.getTitle());
        labelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        labelTitle.setBounds(10, 25, 250, 25);
        contentPane.add(labelTitle);

        //Create Confirmation button
        confirmationButton = new JButton("Confirm loan");
        confirmationButton.setHorizontalAlignment(SwingConstants.CENTER);
        confirmationButton.setBounds(75, 180, 250, 25);
        contentPane.add(confirmationButton);

    }

    private void buildIsbnLabel() {
        BookModel book = (BookModel) article;

        //Create ISBN Label
        labelIsbn = new JLabel("ISBN: " + book.getIsbn());
        labelIsbn.setHorizontalAlignment(SwingConstants.LEFT);
        labelIsbn.setBounds(10, 52, 250, 25);
        contentPane.add(labelIsbn);
    }

    public void addConfirmationButtonListener (ActionListener listener) {
        confirmationButton.addActionListener(listener);
    }

}








