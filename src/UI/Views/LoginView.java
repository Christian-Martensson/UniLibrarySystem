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

        //** Create Login Frame**//
        setTitle("Login");
        setSize(400,170);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //**Create Username Label**//
        JLabel lblUsername = new JLabel("Username:");
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(10, 25, 120, 25);
        contentPane.add(lblUsername);

        //**Create Username TextField**//
        txtUsername = new JTextField();
        //txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Format of String
        txtUsername.setBounds(140, 25, 200, 25);
        contentPane.add(txtUsername);

        //**Create Password Label**//
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));// Format of String
        lblPassword.setBounds(10, 52, 120, 25);
        contentPane.add(lblPassword);

        //**Create Password Textfield**//
        txtPassword = new JPasswordField();
        //txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));// Format of String
        txtPassword.setBounds(140, 52, 200, 25);
        contentPane.add(txtPassword);

        //**Create Login Button**//
        loginButton = new JButton("Login");


        //loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));// Format of String
        loginButton.setBounds(251, 93, 89, 25);
        contentPane.add(loginButton);

        //Create errormessage
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblErrorMessage.setForeground(Color.RED);//Format of setForeground
        //lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));// Format of String
        lblErrorMessage.setBounds(10, 151, 330, 25);
        contentPane.add(lblErrorMessage);


        //Controll
        LoginController controller = new LoginController(this);

    }

    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }

    public void addLoginButtonListener (ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }



    }








