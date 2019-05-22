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
    private JButton addButton;

    private JComboBox<String> addAlternativesDropdown;

    public BottomToolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        loanButton = new JButton("Loan");
        reserveButton = new JButton("Reserve");
        editSelectedItemButton = new JButton("Edit");
        removeSelectedItemButton = new JButton("Remove");
        overdueItemsButton = new JButton("See overdue items");
        addButton = new JButton("Add");

        addAlternativesDropdown = new JComboBox<>();
        addAlternativesDropdown.addItem("Book");
        addAlternativesDropdown.addItem("Movie");
        addAlternativesDropdown.addItem("Magazine");
        addAlternativesDropdown.addItem("User");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(loanButton);
        add(reserveButton);
        add(editSelectedItemButton);
        add(removeSelectedItemButton);
        add(overdueItemsButton);
        add(addButton);
        add(addAlternativesDropdown);
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
    public void addAddButtonListener (ActionListener listener) {
        addButton.addActionListener(listener);
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

    public JButton getAddButton() {
        return addButton;
    }

    public JComboBox<String> getAddAlternativesDropdown() {
        return addAlternativesDropdown;
    }
}
