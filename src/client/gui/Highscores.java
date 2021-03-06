package client.gui;


import client.sdk.Score;
import client.sdk.TableModelScores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Highscores extends JPanel {

	/**
	 * variables/components of this JPanel
	 */

	private JTable highscoresTbl;
	private JTextField userField;
	private JLabel snakeLbl;
	private JLabel loggedInAsLbl;
	private JButton logOutBtn;
	private JButton menuBtn;
	private JScrollPane scrollPane;
	private TableModelScores model;

	/**
	 * Create the panel and components
	 */

	public Highscores() {
		setBackground(new Color(255, 255, 224));
		setLayout(null);
		
		ImageIcon snake = new ImageIcon(this.getClass().getResource("animated-snake-image-0027.gif")); 
		snakeLbl = new JLabel("");
		snakeLbl.setIcon(snake);
		snakeLbl.setBounds(30, 11, 209, 81);
		add(snakeLbl);

		highscoresTbl = new JTable();
		highscoresTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		highscoresTbl.setAutoCreateRowSorter(true);
		highscoresTbl.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
		highscoresTbl.setVisible(true);

		scrollPane = new JScrollPane(highscoresTbl);
		scrollPane.setBounds(40, 103, 357, 164);
		scrollPane.setVisible(true);
		add(scrollPane);
		
		loggedInAsLbl = new JLabel("Logged in as:");
		loggedInAsLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		loggedInAsLbl.setBounds(259, 11, 107, 17);
		add(loggedInAsLbl);
		
		userField = new JTextField();
		userField.setEditable(false);
		userField.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
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

	}

	/**
	 * adding actionlisteners to the JButtons
	 * @param h the actionlistener that is added to the buttons
	 */

	public void addActionListener(ActionListener h){
		logOutBtn.addActionListener(h);
		menuBtn.addActionListener(h);
	}

	/**
	 * getters and setters to some components
	 * @return
	 */
	public JButton getLogOutBtn(){
		return logOutBtn;
	}
	
	public JButton getMenuBtn(){
		return menuBtn;
	}

	public void setUserField(String user){
		userField.setText(user);
	}

	public JTable getHighscoresTbl(){
		return highscoresTbl;
	}

	public void setHighscoresTbl(ArrayList<Score> scores){
		model = new TableModelScores(scores);
		highscoresTbl.setModel(model);
	}
}
