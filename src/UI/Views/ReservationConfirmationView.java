package UI.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Models.Entities.BookModel;

public class ReservationConfirmationView extends JFrame {

    JLabel labelTitle;
    JLabel labelIsbn;
    JButton confirmationButton;

    public ReservationConfirmationView(BookModel bookModel) {

        //** Create Login Frame**//
        setTitle("Reservation confirmation");
        setSize(400,250);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //**Create Title Label**//
        labelTitle = new JLabel("Title: " + bookModel.getTitle());
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        labelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        labelTitle.setBounds(10, 25, 250, 25);
        contentPane.add(labelTitle);

        //**Create ISBN Label**//
        labelIsbn = new JLabel("Title: " + bookModel.getIsbn());
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        labelIsbn.setHorizontalAlignment(SwingConstants.LEFT);
        labelIsbn.setBounds(10, 52, 250, 25);
        contentPane.add(labelIsbn);


        //**Create confirmation button**//
        confirmationButton = new JButton("Confirm reservation");
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        confirmationButton.setHorizontalAlignment(SwingConstants.CENTER);
        confirmationButton.setBounds(75, 180, 250, 25);
        contentPane.add(confirmationButton);

    }

    public void addConfirmationButtonListener (ActionListener listener) {
        confirmationButton.addActionListener(listener);
    }

}








