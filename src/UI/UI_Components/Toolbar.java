package UI.UI_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Toolbar extends JPanel {
	private JButton loginButton;
	private JButton searchButton;
	private JTextField textField;
	private JLabel searchLabel;

	private JComboBox<String> searchAlternativesDropdown;

	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		loginButton = new JButton("Log in");
		textField = new JTextField(10);
		searchLabel = new JLabel("SearchField:");
		searchButton = new JButton("Search");

		searchAlternativesDropdown = new JComboBox<String>();
		searchAlternativesDropdown.addItem("Book");
		searchAlternativesDropdown.addItem("DVD");
		searchAlternativesDropdown.addItem("Magazine");
		searchAlternativesDropdown.addItem("User");

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(loginButton);
		add(searchLabel);
		add(textField);
		add(searchButton);

		add(searchAlternativesDropdown);
	}

	public void addSearchButtonListener (ActionListener listener) {
		searchButton.addActionListener(listener);
	}

	public void addLoginButtonListener (ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JComboBox<String> getSearchAlternativesDropdown() {
		return searchAlternativesDropdown;
	}
}
