package UI.Views;

import javax.swing.*;
import java.awt.*;


public class MessageView extends JFrame {

    private JPanel panel;
    private JTextArea textArea;

    // Constructor
    public MessageView(String text) {
        setTitle("Message");
        setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);


        // Create text area to display receipt
        textArea = new JTextArea( "", 36, 5 );
        textArea.setEditable( false );
        textArea.setBounds( 25, 25, 450, 200 );
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setTextReceipt(text);


        // Create panel to hold button, textfields, label, textarea
        panel = new JPanel( null );
        panel.add(textArea);
        panel.setPreferredSize( new Dimension(500, 250) );
        setLocation(150,150);


        add(panel);
        pack();
        setVisible( true );
    }

    public void setTextReceipt( String s ) {
        textArea.setText( s );
    }

}


