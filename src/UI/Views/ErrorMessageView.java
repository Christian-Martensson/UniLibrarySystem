package UI.Views;

import javax.swing.*;
import java.awt.*;


public class ErrorMessageView extends JFrame {


    public ErrorMessageView(String errorMessage) {

        //** Create Login Frame**//
        setTitle("Error!");
        setSize(400,250);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //**Create Username Label**//
        JLabel labelError = new JLabel(errorMessage);
        //lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12)); // Format of String
        labelError.setHorizontalAlignment(SwingConstants.RIGHT);
        labelError.setBounds(10, 25, 250, 25);
        contentPane.add(labelError);

    }

}








