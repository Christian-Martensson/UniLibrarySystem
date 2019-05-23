package UI.Views;

import javax.swing.*;
import java.awt.*;



public class OverdueItemsView extends JFrame {

    Container contentPane;
    private JScrollPane scrollPane;

    public OverdueItemsView(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(600,350));
        table.setFillsViewportHeight(true);

        //Create New Pop Up View
        setTitle("Overdue items");
        setSize(650,450);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

    }

}








