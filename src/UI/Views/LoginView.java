package UI.Views;

import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.LoginController;

public class LoginView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblErrorMessage;


    public LoginView() {

        //** Create Login Frame**//
        setTitle("Login");
        setSize(400,400);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //**Create Username Label**//
        JLabel lblUsername = new JLabel("Username:");
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(10, 11, 120, 25);
        contentPane.add(lblUsername);

        //**Create Username TextField**//
        txtUsername = new JTextField();
        //txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Format of String
        txtUsername.setBounds(140, 11, 200, 25);
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


        //Controll
        LoginController controller = new LoginController(this);

        //**Create Login Button**//
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener(){

           @Override
        public void actionPerformed(ActionEvent arg0) {
               controller.checkCredentials(txtUsername.getText(), new String(txtPassword.getPassword()));
           }
        });

        //btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));// Format of String
        btnLogin.setBounds(251, 93, 89, 25);
        contentPane.add(btnLogin);

        //Create errormessage
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        //lblErrorMessage.setForeground(Color.RED);//Format of setForeground
        //lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));// Format of String
        lblErrorMessage.setBounds(10, 151, 330, 25);
        contentPane.add(lblErrorMessage);

    }

    //Error message
    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }


   // public void showVisible(Boolean){
   //LoginView view = new LoginView();
    // view.setVisible(true);
    }








