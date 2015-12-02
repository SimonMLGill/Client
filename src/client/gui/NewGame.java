package client.gui;

import client.sdk.Game;
import client.sdk.TableModelOpenGames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class NewGame extends JPanel {

	// variables/components that populate this JPanel

	private JTextField moveField;
	private JTable availableGamesTbl;
	private JTextField userField;
	private JLabel snakeLbl;
	private JLabel availableGamesLbl;
	private JLabel nextMovesLbl;
	private JButton moveBtn;
	private JTextArea logArea;
	private JButton logBtn;
	private JButton runGameBtn;
	private JLabel loggedInAsLbl;
	private JButton logOutBtn;
	private JButton menuBtn;
	private JRadioButton showLogRdbtn;
	private JRadioButton hideLogRdbtn;
	private JScrollPane scrollPane;
	private TableModelOpenGames model;


	/**
	 * Create the panel.
	 */

	// components to the JPanel
	public NewGame() {
		setBackground(new Color(255, 255, 224));
		setLayout(null);
		
		ImageIcon snake = new ImageIcon(this.getClass().getResource("animated-snake-image-0027.gif")); 
		snakeLbl = new JLabel("");
		snakeLbl.setIcon(snake);
		snakeLbl.setBounds(30, 11, 209, 81);
		add(snakeLbl);
		
		availableGamesLbl = new JLabel("Available Games:");
		availableGamesLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		availableGamesLbl.setBounds(30, 119, 118, 14);
		add(availableGamesLbl);
		
		nextMovesLbl = new JLabel("Enter next moves:");
		nextMovesLbl.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		nextMovesLbl.setBounds(158, 120, 134, 14);
		add(nextMovesLbl);
		
		moveField = new JTextField();
		moveField.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
		moveField.setBounds(291, 117, 89, 20);
		add(moveField);
		moveField.setColumns(10);

		availableGamesTbl = new JTable();
		availableGamesTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		availableGamesTbl.setAutoCreateRowSorter(true);
		//scrollPane.setViewportView(availableGamesTbl);
		availableGamesTbl.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
		availableGamesTbl.setVisible(true);

		scrollPane = new JScrollPane(availableGamesTbl);
		scrollPane.setBounds(30, 144, 126, 96);
		scrollPane.setVisible(true);
		add(scrollPane);


		moveBtn = new JButton("Commit");
		moveBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		moveBtn.setBounds(291, 145, 89, 23);
		add(moveBtn);
		
		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		logArea.setBounds(179, 145, 94, 53);
		logArea.setVisible(false);
		add(logArea);
		/*
		logBtn = new JButton("Log");
		logBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		logBtn.setBounds(179, 209, 89, 23);
		add(logBtn);
		*/
		runGameBtn = new JButton("Run Game");
		runGameBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		runGameBtn.setBounds(278, 209, 105, 23);
		add(runGameBtn);
		
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

		showLogRdbtn = new JRadioButton("Show Log");
		showLogRdbtn.setBounds(183, 205, 89, 23);
		showLogRdbtn.setSelected(false);
		add(showLogRdbtn);

		hideLogRdbtn = new JRadioButton("Hide Log");
		hideLogRdbtn.setBounds(183, 231, 89, 23);
		hideLogRdbtn.getModel().setSelected(true);
		add(hideLogRdbtn);

	}

	// adding actionlisteners the JButtons and JRadioButtons
	
	public void addActionListener(ActionListener n){
		moveBtn.addActionListener(n);
		runGameBtn.addActionListener(n);
		logOutBtn.addActionListener(n);
		menuBtn.addActionListener(n);
		showLogRdbtn.addActionListener(n);
		hideLogRdbtn.addActionListener(n);
	}

	// setters and getters to some of the components

	public JButton getMoveBtn(){
		return moveBtn;
	}
	
	public JButton getLogBtn(){
		return logBtn;
	}
	
	public JButton getRunGameBtn(){
		return runGameBtn;
	}
	
	public JButton getLogOutBtn(){
		return logOutBtn;
	}
	
	public JButton getMenuBtn(){
		return menuBtn;
	}
	
	public String getMoveField(){
		return moveField.getText();
	}
	
	public void setLogArea(String log){
		log = moveField.getText();
		logArea.setText(log);
	}
	public JTextArea getLogArea(){
		return logArea;
	}

	public JRadioButton getShowLogRdbtn(){
		return showLogRdbtn;
	}
	public JRadioButton getHideLogRdbtn(){
		return hideLogRdbtn;
	}

	public void setUserField(String user){
		userField.setText(user);

	}

	public JTable getAvailableGamesTbl(){
		return availableGamesTbl;
	}

	public void setAvailableGamesTbl(ArrayList<Game> games){
		model = new TableModelOpenGames(games);
		availableGamesTbl.setModel(model);
	}

	public void setMoveField(String text){
		moveField.setText(text);
	}
}