package UI.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.LoginController;

public class LoanView extends JFrame {

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

    public LoanView(String title) {

        //** Create Login Frame**//
        setTitle("Loan confirmation");
        setSize(400,250);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //**Create Username Label**//
        JLabel lblUsername = new JLabel("Title: " + title);
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(10, 25, 120, 25);
        contentPane.add(lblUsername);


    }

}








