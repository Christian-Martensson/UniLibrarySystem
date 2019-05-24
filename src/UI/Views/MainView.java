package UI.Views;

import UI.UI_Components.BottomToolbar;
import UI.UI_Components.ScrollPanel;
import UI.UI_Components.Toolbar;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {
	
	private ScrollPanel scrollPanel;
	private Toolbar toolbar;
	private BottomToolbar bottomToolBar;

	// Create Main View Frame
	public MainView() {
		super("Uni Library System");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		scrollPanel = new ScrollPanel();
		bottomToolBar = new BottomToolbar();


		add(toolbar, BorderLayout.NORTH);
		add(scrollPanel, BorderLayout.CENTER);
		add(bottomToolBar, BorderLayout.SOUTH);

		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public ScrollPanel getScrollPanel() {
		return scrollPanel;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public BottomToolbar getBottomToolBar() {
		return bottomToolBar;
	}
}
