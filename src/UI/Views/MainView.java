package UI.Views;

import UI.UI_Components.BottomToolbar;
import UI.UI_Components.FormPanel;
import UI.UI_Components.ScrollPanel;
import UI.UI_Components.Toolbar;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {
	
	private ScrollPanel scrollPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private BottomToolbar bottomToolBar;

	
	public MainView() {
		super("Uni Library System");
		
		setLayout(new BorderLayout());
		
		toolbar = new Toolbar();
		scrollPanel = new ScrollPanel();
		formPanel = new FormPanel();
		bottomToolBar = new BottomToolbar();
		
		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(scrollPanel, BorderLayout.CENTER);
		add(bottomToolBar, BorderLayout.SOUTH);
		
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public ScrollPanel getScrollPanel() {
		return scrollPanel;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public BottomToolbar getBottomToolBar() {
		return bottomToolBar;
	}
}
