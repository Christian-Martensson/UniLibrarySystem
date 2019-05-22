package UI.UI_Components;


import javax.swing.*;
import java.awt.*;


public class ScrollPanel extends JPanel {

	private JScrollPane scrollPane;
	private static JTable table;
	
	public ScrollPanel() {

		table = new JTable();

		table.setPreferredScrollableViewportSize(new Dimension(600,350));
		table.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(table);
		
		setLayout(new FlowLayout());
		
		add(scrollPane, BorderLayout.CENTER);
	}

	public void appendSearchResult(JTable table) {
		this.table = table;
		scrollPane.getViewport().add(table);

	}

	public static JTable getTable() {
		return table;
	}
}
