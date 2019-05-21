package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BottomToolbar extends JPanel {
    private JButton borrowButton;
    private JButton reserveButton;
    private JButton editSelectedItemButton;
    private JButton removeSelectedItemButton;
    private JButton overdueItemsButton;

    public BottomToolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        borrowButton = new JButton("Loan");
        reserveButton = new JButton("Reserve");
        editSelectedItemButton = new JButton("Edit");
        removeSelectedItemButton = new JButton("Remove");
        overdueItemsButton = new JButton("See overdue items");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(borrowButton);
        add(reserveButton);
        add(editSelectedItemButton);
        add(removeSelectedItemButton);
        add(overdueItemsButton);
    }

    public JButton getBorrowButton() {
        return borrowButton;
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
