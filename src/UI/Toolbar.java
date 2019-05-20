package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Toolbar extends JPanel {
	private JButton loginButton;
	private JButton searchButton;
	private JTextField textField;
	private JLabel searchLabel;

	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		loginButton = new JButton("Log in");
		textField = new JTextField(10);
		searchLabel = new JLabel("SearchField:");
		searchButton = new JButton("Search");

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(loginButton);
		add(searchLabel);
		add(textField);
		add(searchButton);

	}


	public void addSearchButtonListener (ActionListener listenForSearchButton) {
		searchButton.addActionListener(listenForSearchButton);
	}

	public void addLoginButtonListener (ActionListener listenForLoginButton) {
		loginButton.addActionListener(listenForLoginButton);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getLoginButton() {

		return loginButton;
	}

}
