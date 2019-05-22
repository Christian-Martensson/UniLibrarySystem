package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BottomToolbar extends JPanel {
    private JButton borrowButton;
    private JButton reserveButton;
    private JButton editButton;
    private JButton deleteButton;



    public BottomToolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        borrowButton = new JButton("Borrow");
        reserveButton = new JButton("Reserve");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");


        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(borrowButton);
        add(reserveButton);
        add(editButton);
        add(deleteButton);

    }

    /*
    public void addReserveButtonListener (ActionListener listenForSearchButton) {
        reserveButton.addActionListener(listenForSearchButton);
    }

    public void addBorrowButtonListener (ActionListener listenForLoginButton) {
        borrowButton.addActionListener(listenForLoginButton);
    }

    public void addEditButtonListener (ActionListener listenForEditButton) {
        editButton.addActionListener(listenForEditButton);
    }

    public void addDeleteButtonListener (ActionListener listenForDeleteButton) {
        deleteButton.addActionListener(listenForDeleteButton);
    }
    */

}
