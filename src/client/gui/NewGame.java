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
	private JComboBox usersGames;
	private JButton runGameBtn;
	private JLabel loggedInAsLbl;
	private JButton logOutBtn;
	private JButton menuBtn;
	private JRadioButton createGameRdbtn;
	private JRadioButton joinGameRdbtn;
	private JScrollPane scrollPane;
	private TableModelOpenGames model;
	private JButton deleteGameBtn;


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
		
		usersGames = new JComboBox();
		usersGames.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		usersGames.setBounds(179, 145, 94, 23);
		usersGames.setVisible(true);
		add(usersGames);

		deleteGameBtn = new JButton("Delete");
		deleteGameBtn.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		deleteGameBtn.setBounds(179, 178, 89, 23);
		add(deleteGameBtn);

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

		createGameRdbtn = new JRadioButton("Create Game");
		createGameRdbtn.setBounds(173, 205, 99, 23);
		//createGameRdbtn.setSelected(false);
		add(createGameRdbtn);

		joinGameRdbtn = new JRadioButton("Join Game");
		joinGameRdbtn.setBounds(173, 231, 99, 23);
		//joinGameRdbtn.setSelected(true);
		add(joinGameRdbtn);

	}

	// adding actionlisteners the JButtons and JRadioButtons
	
	public void addActionListener(ActionListener n){
		moveBtn.addActionListener(n);
		runGameBtn.addActionListener(n);
		logOutBtn.addActionListener(n);
		menuBtn.addActionListener(n);
		createGameRdbtn.addActionListener(n);
		joinGameRdbtn.addActionListener(n);
		deleteGameBtn.addActionListener(n);
	}

	// setters and getters to some of the components

	public JButton getMoveBtn(){
		return moveBtn;
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

	public JButton getDeleteGameBtn(){
		return deleteGameBtn;
	}
	
	public JComboBox getUsersGames(){
		return usersGames;
	}

	public void setGamesInUsersGames(ArrayList<Game> games){
		usersGames.removeAllItems();
		for(Game game: games){
			usersGames.addItem(game.getName());
		}
	}

	public String getSelectedGame(){
		return (String) usersGames.getSelectedItem();
	}

	public void removeGame(){
		usersGames.removeItemAt(usersGames.getSelectedIndex());
	}

	public JRadioButton getCreateGameRdbtn(){
		return createGameRdbtn;
	}
	public JRadioButton getJoinGameRdbtn(){
		return joinGameRdbtn;
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

	public JScrollPane getScrollPane(){
		return scrollPane;
	}

	public void setMoveField(String text){
		moveField.setText(text);
	}
}