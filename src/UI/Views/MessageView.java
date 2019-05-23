package UI.Views;

import javax.swing.*;
import java.awt.*;


public class MessageView extends JFrame {


    private JFrame frame;
    private JPanel panel;
    private JTextArea  taReceipt;

    // Constructor
    public MessageView(String text) {
        setTitle("Error");
        setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);


        // Create text area to display receipt
        taReceipt = new JTextArea( "", 36, 5 );
        taReceipt.setEditable( false );
        taReceipt.setBounds( 25, 25, 450, 200 );
        taReceipt.setFont(new Font("Courier", Font.PLAIN, 12));
        setTextReceipt(text);



        // Create panel to hold button, textfields, label, textarea
        panel = new JPanel( null );
        panel.add( taReceipt );
        panel.setPreferredSize( new Dimension(500, 250) );
        setLocation(150,150);


        add(panel);
        pack();
        setVisible( true );
    }

    public void setTextReceipt( String s ) {
        taReceipt.setText( s );
    }

}


