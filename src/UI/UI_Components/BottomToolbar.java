package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BottomToolbar extends JPanel {
    private JButton loanButton;
    private JButton reserveButton;
    private JButton editSelectedItemButton;
    private JButton removeSelectedItemButton;
    private JButton overdueItemsButton;

    public BottomToolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        loanButton = new JButton("Loan");
        reserveButton = new JButton("Reserve");
        editSelectedItemButton = new JButton("Edit");
        removeSelectedItemButton = new JButton("Remove");
        overdueItemsButton = new JButton("See overdue items");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(loanButton);
        add(reserveButton);
        add(editSelectedItemButton);
        add(removeSelectedItemButton);
        add(overdueItemsButton);
    }

    public void addLoanButtonListener (ActionListener listener) {
        loanButton.addActionListener(listener);
    }

    public void addReserveButtonListener (ActionListener listener) {
        reserveButton.addActionListener(listener);
    }

    public void addEditButtonListener(ActionListener listener) {
        editSelectedItemButton.addActionListener(listener);
    }

    public void addRemoveButtonListener(ActionListener listener) {
        removeSelectedItemButton.addActionListener(listener);
    }

    public void addOverdueItemsButtonListener (ActionListener listener) {
        overdueItemsButton.addActionListener(listener);
    }

    public JButton getLoanButton() {
        return loanButton;
    }

    public JButton getReserveButton() {
        return reserveButton;
    }

    public JButton getEditSelectedItemButton() {
        return editSelectedItemButton;
    }

    public JButton getRemoveSelectedItemButton() {
        return removeSelectedItemButton;
    }

    public JButton getOverdueItemsButton() {
        return overdueItemsButton;
    }
}
