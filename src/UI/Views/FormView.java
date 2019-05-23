package UI.Views;

import Models.Entities.BookModel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FormView extends JFrame {

    private JTextField[] textFields;
    JButton submitButton;
    JPanel panelPosition;


    // Create a form with the specified labels, tools, and sizes.
    public FormView(String[] labels, String buttonText) {
        setTitle(buttonText);
        setLocation(200,200);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(labels.length + 1, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length + 1, 1));
        contentPane.add(labelPanel, BorderLayout.WEST);
        contentPane.add(fieldPanel, BorderLayout.CENTER);
        textFields = new JTextField[labels.length];

        //Create Labels and TextFields
        for (int i = 0; i < labels.length; i += 1) {
            textFields[i] = new JTextField();
            textFields[i].setColumns(15);

            //Label position right
            JLabel labelPosition = new JLabel(labels[i], JLabel.RIGHT);
            labelPosition.setLabelFor(textFields[i]);

            //Panel position FlowLayout left
            labelPanel.add(labelPosition);
            panelPosition = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelPosition.add(textFields[i]);
            fieldPanel.add(panelPosition);
        }

        submitButton = new JButton(buttonText);

        contentPane.add(submitButton, BorderLayout.SOUTH);
        pack();
    }

    public void addSubmitButtonListener (ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public static FormView generateFormUser(String buttonText) {
        String[] labels = { "First Name:", "Last Name: ", "Personal ID:", "User Type:",
                "Street:", "Zip code:", "City:", "Country:", "Telephone:", "Mail:",
                "Username:", "Password:"};

        FormView view = new FormView(labels, buttonText);


        return view;
    }

    public static FormView generateFormBook(String buttonText) {
        String[] labels = { "ISBN:", "Title:", "Article type:", "Publisher:", "Publication year:",
        "Genre:", "Author:", };

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    public static FormView generateFormMagazine(String buttonText) {
        String[] labels = { "Magazine number:", "Title:", "Publisher:", "Publication date:",
                "Genre:"};

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    public static FormView generateFormMovie(String buttonText) {
        String[] labels = { "Title:", "Publication Year", "Producer:", "Genre:",
                "Article Type:"};

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    public void fillFieldsWith(BookModel book) {
        this.setText(0, book.getIsbn());
        this.setText(1, book.getTitle());
        this.setText(2, book.getArticleType());
        this.setText(3, book.getPublisher());
        this.setText(4, book.getPublicationYear());
        this.setText(5, book.getGenre());
        this.setText(6, book.getCreator());
        textFields[0].setEditable(false);

    }

    //GetText
    public String getText(int i) {
        return (textFields[i].getText());
    }
    public void setText(int i, String text) {
        textFields[i].setText(text);
    }
}
