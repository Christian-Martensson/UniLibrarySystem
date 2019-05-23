package UI.Views;

import Models.Entities.Article;

import javax.swing.*;
import java.awt.*;

public class LoanReceiptView extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JTextArea  taReceipt;
    private Article article;

    // Constructor
    public LoanReceiptView(Article article, String text) {
        this.article = article;

        setTitle("Your receipt");
        setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);


        // Create text area to display receipt
        taReceipt = new JTextArea( "", 36, 5 );
        taReceipt.setEditable( false );
        taReceipt.setBounds( 0, 0, 300, 400 );
        taReceipt.setFont(new Font("Courier", Font.PLAIN, 12));
        setTextReceipt(text);



        // Create panel to hold button, textfields, label, textarea
        panel = new JPanel( null );
        panel.add( taReceipt );
        panel.setPreferredSize( new Dimension(300, 400) );



        add(panel);
        pack();
        setVisible( true );
    }


    public void setTextReceipt( String s ) {
            taReceipt.setText( s );
        }

    public JTextArea getTaReceipt() {
        return taReceipt;
    }
}

