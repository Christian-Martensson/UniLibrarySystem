package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Toolbar extends JPanel {
	private JButton loginButton;
	private JButton searchButton;
	private JTextField textField;
	private JLabel searchLabel;
	private JLabel checkboxLabel;
	private JCheckBox checkbox;

	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		loginButton = new JButton("Log in");
		textField = new JTextField(10);
		searchLabel = new JLabel("SearchField:");
		searchButton = new JButton("Search");
		checkboxLabel = new JLabel("User search:");
		checkbox = new JCheckBox();

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(loginButton);
		add(searchLabel);
		add(textField);
		add(searchButton);
		add(checkboxLabel);
		add(checkbox);
	}

	public void addSearchButtonListener (ActionListener listener) {
		searchButton.addActionListener(listener);
	}

	public void addLoginButtonListener (ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	public void addCheckboxListener (ActionListener listener) {
		checkbox.addActionListener(listener);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JLabel getCheckboxLabel() {
		return checkboxLabel;
	}

	public JCheckBox getCheckbox() {
		return checkbox;
	}
}
