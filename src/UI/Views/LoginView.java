package UI.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.LoginController;

public class LoginView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JLabel lblErrorMessage;

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public LoginView() {

        //Create Pop Up Login View
        setTitle("Login");
        setSize(400,250);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //Create Username Label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(10, 25, 120, 25);
        contentPane.add(lblUsername);

        //Create Username TextField
        txtUsername = new JTextField();
        txtUsername.setBounds(140, 25, 200, 25);
        contentPane.add(txtUsername);

        //Create Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(10, 52, 120, 25);
        contentPane.add(lblPassword);

        //Create Password Textfield
        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 52, 200, 25);
        contentPane.add(txtPassword);

        //Create Login Button
        loginButton = new JButton("Log in");
        loginButton.setBounds(251, 93, 89, 25);
        contentPane.add(loginButton);

        //Create errormessage if login fails
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        lblErrorMessage.setBounds(10, 151, 330, 25);
        contentPane.add(lblErrorMessage);
    }

    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }

    public void addLoginButtonListener (ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}








