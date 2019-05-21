package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BottomToolbar extends JPanel {
    private JButton borrowButton;
    private JButton reserveButton;



    public BottomToolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        borrowButton = new JButton("Borrow");
        reserveButton = new JButton("Reserve");


        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(borrowButton);
        add(reserveButton);

    }

    /*public void addSearchButtonListener (ActionListener listenForSearchButton) {
        reserveButton.addActionListener(listenForSearchButton);
    }

    public void addLoginButtonListener (ActionListener listenForLoginButton) {
        borrowButton.addActionListener(listenForLoginButton);
    }*/


}
