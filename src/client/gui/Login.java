package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Login extends JPanel {

	// variables/components of this JPanel

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JLabel snakeLbl;
	private JLabel titleLbl;
	private JButton loginBtn;
	
	/**
	 * Create the panel.
	 */

	// components of the JPanel

	public Login() {
		setBackground(new Color(255, 255, 224));
		setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		usernameField.setBounds(121, 116, 183, 36);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(121, 183, 183, 36);
		add(passwordField);
		
		usernameLbl = new JLabel("Username:");
		usernameLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		usernameLbl.setBounds(30, 127, 81, 17);
		add(usernameLbl);
		
		passwordLbl = new JLabel("Password:");
		passwordLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		passwordLbl.setBounds(30, 194, 81, 14);
		add(passwordLbl);
		
		ImageIcon snake = new ImageIcon(this.getClass().getResource("animated-snake-image-0027.gif")); 
		snakeLbl = new JLabel("");
		snakeLbl.setBounds(30, 11, 209, 81);
		snakeLbl.setIcon(snake);
		add(snakeLbl);
		
		titleLbl = new JLabel("SNAKESNAKESNAKESNAKESNAKE");
		titleLbl.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
		titleLbl.setBounds(249, 11, 175, 81);
		add(titleLbl);
		
		loginBtn = new JButton("LOGIN");
		loginBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		loginBtn.setBounds(335, 127, 89, 81);
		add(loginBtn);

	}

	// adding actionlisteners to the JButton
	
	public void addActionListener (ActionListener l){
		loginBtn.addActionListener(l);
	}

	// method that clears the JTextfields

	public void clearFields(){
		usernameField.setText("");
		passwordField.setText("");
	}

	// getters and setters to some components
	
	public JButton getLoginBtn(){
		return loginBtn;
	}
	
	public String getUsernameField(){
		String username = usernameField.getText();
		return username;
	}
	
	public String getPasswordField(){
		String password = passwordField.getText();
		return password;
	}

}
