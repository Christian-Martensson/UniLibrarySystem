package UI.Views;

import UI.UI_Components.BottomToolbarLoans;

import javax.swing.*;
import java.awt.*;


public class MyLoansView extends JFrame {

    private Container contentPane;
    private JScrollPane scrollPane;
    private BottomToolbarLoans bottomToolbar;
    private JTable table;

    public MyLoansView(JTable table) {
        this.table = table;

        table.setPreferredScrollableViewportSize(new Dimension(600,350));
        table.setFillsViewportHeight(true);
        //** Create Login Frame**//
        setTitle("My loans");
        setSize(650,450);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);//Keep the application running but close Frame
        bottomToolbar = new BottomToolbarLoans();
        contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(bottomToolbar, BorderLayout.SOUTH);

    }

    public BottomToolbarLoans getBottomToolbar() {
        return bottomToolbar;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getTable() {
        return table;
    }
}








