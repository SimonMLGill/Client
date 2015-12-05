package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class About extends JPanel {

	/**
	 * variables/components to this JPanel
	 */

	private JTextField userField;
	private JLabel loggedInAsLbl;
	private JLabel snakeLbl;
	private JButton logOutBtn;
	private JButton menuBtn;
	private JTextArea infoArea;
	private JButton moreInfoBtn;
	private JButton createGameBtn;
	private JButton joinGameBtn;
	private JButton deleteGameBtn;
	
	/**
	 * Create the panel, and the components
	 */

	public About() {
		setBackground(new Color(255, 255, 224));
		setLayout(null);
		
		ImageIcon snake = new ImageIcon(this.getClass().getResource("animated-snake-image-0027.gif")); 
		snakeLbl = new JLabel("");
		snakeLbl.setIcon(snake);
		snakeLbl.setBounds(30, 11, 209, 81);
		add(snakeLbl);
		
		loggedInAsLbl = new JLabel("Logged in as:");
		loggedInAsLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		loggedInAsLbl.setBounds(259, 11, 107, 17);
		add(loggedInAsLbl);
		
		userField = new JTextField();
		userField.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
		userField.setEditable(false);
		userField.setBounds(259, 39, 119, 20);
		add(userField);
		userField.setColumns(10);
		
		logOutBtn = new JButton("Log Out");
		logOutBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		logOutBtn.setBounds(259, 69, 89, 23);
		add(logOutBtn);
		
		menuBtn = new JButton("Menu");
		menuBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		menuBtn.setBounds(351, 69, 89, 23);
		add(menuBtn);

		createGameBtn = new JButton("Create Game");
		createGameBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		createGameBtn.setBounds(57, 119, 131, 23);
		add(createGameBtn);

		joinGameBtn = new JButton("Join Game");
		joinGameBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		joinGameBtn.setBounds(57, 167, 131, 23);
		add(joinGameBtn);

		deleteGameBtn = new JButton("Delete Game");
		deleteGameBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		deleteGameBtn.setBounds(57, 211, 131, 23);
		add(deleteGameBtn);

		moreInfoBtn = new JButton("More Info");
		moreInfoBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		moreInfoBtn.setBounds(277, 118, 112, 23);
		add(moreInfoBtn);

	}

	/**
	 * adding actionlisteners to buttons
	 * @param a the actionlistener that is added to the buttons
	 */

	public void addActionListener(ActionListener a){
		logOutBtn.addActionListener(a);
		menuBtn.addActionListener(a);
		createGameBtn.addActionListener(a);
		joinGameBtn.addActionListener(a);
		deleteGameBtn.addActionListener(a);
		moreInfoBtn.addActionListener(a);
	}

	/**
	 *
	 * setters and getters to some of the components
	 * @return
	 */

	public JButton getLogOutBtn(){
		return logOutBtn;
	}
	
	public JButton getMenuBtn(){
		return menuBtn;
	}

	public JButton getCreateGameBtn(){
		return createGameBtn;
	}

	public JButton getJoinGameBtn(){
		return joinGameBtn;
	}

	public JButton getDeleteGameBtn(){
		return deleteGameBtn;
	}

	public JButton getMoreInfoBtn(){
		return moreInfoBtn;
	}

	public void setUserField(String user){
		userField.setText(user);
	}
}
