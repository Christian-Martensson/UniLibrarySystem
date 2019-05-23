package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BottomToolbarLoans extends JPanel {

    private JButton returnLoanButton;

    public BottomToolbarLoans() {
        setBorder(BorderFactory.createEtchedBorder());

        returnLoanButton = new JButton("Return");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(returnLoanButton);
    }

    public void addReturnLoanButtonListener (ActionListener listener) {
        returnLoanButton.addActionListener(listener);
    }
}
