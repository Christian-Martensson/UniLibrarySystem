package UI.Views;

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

    public static FormView generateFormUser() {
        String[] labels = { "First Name:", "Last Name: ", "Personal ID:", "User Type:",
                "Street:", "Zip code:", "City:", "Country:", "Telephone:", "Mail:",
                "Username:", "Password:"};


        FormView view = new FormView(labels, "Add user");


        return view;
    }
    public static FormView generateFormBook() {
        String[] labels = { "ISBN:", "Title:", "Article type:", "Publisher:", "Publication year:",
        "Genre:", "Author:", };

        FormView view = new FormView(labels, "Add book");
        return view;
    }

    public static FormView generateFormMagazine() {
        String[] labels = { "Magazine number:", "Title:", "Publisher:", "Publication date:",
                "Genre:"};

        FormView view = new FormView(labels, "Add Magazine");
        return view;
    }

    public static FormView generateFormMovie() {
        String[] labels = { "Title:", "Publication Year", "Producer:", "Genre:",
                "Article Type:"};

        FormView view = new FormView(labels, "Add movie");
        return view;
    }

    //GetText
    public String getText(int i) {
        return (textFields[i].getText());
    }
}
