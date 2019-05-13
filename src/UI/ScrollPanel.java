package UI;


import javax.swing.*;
import java.awt.*;


public class ScrollPanel extends JPanel {

	//private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTable table;
	
	public ScrollPanel() {
		//textArea = new JTextArea();
		String[] columnNames = {"Title",
				"Publication Year"};
		Object[][] data = {{"Human architecture", "1994"}, {"Human Biology", "1999"}};

		table = new JTable(data, columnNames);

		table.setPreferredScrollableViewportSize(new Dimension(300,200));
		table.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(table);
		
		setLayout(new FlowLayout());
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	/*public void appendText(String text) {
		textArea.append(text);
	}*/

	public void appendSearchResult(JTable table) {
		scrollPane.getViewport().add(table);

	}
}
