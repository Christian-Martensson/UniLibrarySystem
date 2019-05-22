package UI.Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FormUserView extends JFrame {

    private JTextField[] textFields;

    // Create a form with the specified labels, tools, and sizes.
    public FormUserView(String[] labels, String buttonText) {
        setTitle("Add new user");
        setLocation(200,200);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
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
            JPanel panelPosition = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelPosition.add(textFields[i]);
            fieldPanel.add(panelPosition);
        }

        JButton submit = new JButton(buttonText);

        contentPane.add(submit, BorderLayout.SOUTH);
        pack();

    }

    public static FormUserView generateFormUser() {
        String[] labels = { "First Name:", "Last Name: ", "Personal ID:", "User Type:",
                "Street:", "Zip code:", "City:", "Country:", "Telephone:", "Mail:",
                "Username:", "Password:"};


        FormUserView view = new FormUserView(labels, "Add user");
        return view;
    }
    public static FormUserView generateFormBook() {
        String[] labels = { "ISBN:", "Title:", "Article type:", "Publisher:", "Publication year:",
        "Genre:", "Author:", };

        FormUserView view = new FormUserView(labels, "Add book");
        return view;
    }

    public static FormUserView generateFormMagazine() {
        String[] labels = { "Magazine number:", "Title:", "Publisher:", "Publication date:",
                "Genre:"};

        FormUserView view = new FormUserView(labels, "Add Magazine");
        return view;
    }

    public static FormUserView generateFormMovie() {
        String[] labels = { "Title:", "Publication Year", "Producer:", "Genre:",
                "Article Type:"};

        FormUserView view = new FormUserView(labels, "Add movie");
        return view;
    }

    //GetText
    public String getText(int i) {
        return (textFields[i].getText());
    }
}
