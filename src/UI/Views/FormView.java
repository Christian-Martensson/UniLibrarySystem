package UI.Views;

import Models.Entities.BookModel;
import Models.Entities.MagazineModel;
import Models.Entities.MovieModel;
import Models.Entities.UserModel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FormView extends JFrame {

    private JTextField[] textFields;
    private JButton submitButton;
    private JPanel panelPosition;


    // Create a form with the specified labels, tools, and sizes.
    public FormView(String[] labels, String buttonText) {
        setTitle(buttonText);
        setLocation(200,200);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(labels.length +1 , 1));
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

            //Panel position with FlowLayout
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

    //Form for User
    public static FormView generateFormUser(String buttonText) {
        String[] labels = { "First Name:", "Last Name: ", "Personal ID:", "User Type:",
                "Street:", "Zip code:", "City:", "Country:", "Telephone:", "Mail:",
                "Username:", "Password:"};

        FormView view = new FormView(labels, buttonText);

        return view;
    }

    //Form for EditUser
    public static FormView generateFormEditUser(String buttonText) {
        String[] labels = { "User ID", "First Name:", "Last Name: ", "Personal ID:", "User Type:",
                "Street:", "Zip code:", "City:", "Country:", "Telephone:", "Mail:",
                "Username:", "Password:"};

        FormView view = new FormView(labels, buttonText);

        return view;
    }

    //Form for Book
    public static FormView generateFormBook(String buttonText) {
        String[] labels = { "ISBN:", "Title:", "Article type:", "Publisher:", "Publication year:",
        "Genre:", "Author:", };

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    //Form for new Magazine
    public static FormView generateFormMagazine(String buttonText) {
        String[] labels = { "Magazine number:", "Title:", "Publisher:", "Publication date:",
                "Genre:"};

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    //Form for edit Magazine
    public static FormView generateFormEditMagazine(String buttonText) {
        String[] labels = { "Magazine ID", "Magazine number:", "Title:", "Publisher:", "Publication date:",
                "Genre:"};

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    //Form for new Movie
    public static FormView generateFormMovie(String buttonText) {
        String[] labels = { "Title:", "Publication Year", "Producer:", "Genre:"};

        FormView view = new FormView(labels, buttonText);
        return view;
    }

    //Form for edit Movie
    public static FormView generateFormEditMovie(String buttonText) {
        String[] labels = { "Movie ID", "Title:", "Publication Year", "Producer:", "Genre:"};

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
    public void fillFieldsWith(MovieModel movie) {
        this.setText(0, Integer.toString(movie.getMovieId()));
        this.setText(1, movie.getTitle());
        this.setText(2, movie.getPublicationYear());
        this.setText(3, movie.getCreator());
        this.setText(4, movie.getGenre());
        textFields[0].setEditable(false);
    }
    public void fillFieldsWith(MagazineModel magazine) {
        this.setText(0, Integer.toString(magazine.getMagazineId()));
        this.setText(1, Integer.toString(magazine.getMagazineNr()));
        this.setText(2, magazine.getTitle());
        this.setText(3, magazine.getPublisher());
        this.setText(4, magazine.getPublicationDate().toString());
        this.setText(5, magazine.getGenre());
        textFields[0].setEditable(false);
    }
    public void fillFieldsWith(UserModel user) {
        this.setText(0, Integer.toString(user.getUserId()));
        this.setText(1, user.getFirstName());
        this.setText(2, user.getLastName());
        this.setText(3, user.getPersonalId());
        this.setText(4, user.getUserType());
        this.setText(5, "");
        this.setText(6, "");
        this.setText(7, "");
        this.setText(8, "");
        this.setText(9, "");
        this.setText(10, "");
        this.setText(11, user.getUsername());
        this.setText(12, user.getPassword());
        textFields[0].setEditable(false);
        textFields[11].setEditable(false);
    }

    //GetText in TextFields
    public String getText(int i) {
        return (textFields[i].getText());
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    //SetText in TextFields
    public void setText(int i, String text) {
        textFields[i].setText(text);
    }
}
