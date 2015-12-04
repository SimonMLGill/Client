package client.logic;

import client.gui.SnakeScreen;
import client.sdk.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Logic {

	// logic/controller class that connects the backend with the frontend
	// creating variables of the screen-, backendlogic-, connector to server- and user class

	private SnakeScreen snakeScreen;
	private SdkLogic sdkLogic;
	private ServerConnection serverConnection;
	private User currentUser;
	private Game game;
	private ArrayList<Game> games;
	private ArrayList<Game> gamesByUser;
	private ArrayList<User > users;
	private ArrayList<Score> scores;
	// creating objects of the aforementioned classes

	public Logic(){
		snakeScreen = new SnakeScreen();
		snakeScreen.setVisible(true);

		serverConnection = new ServerConnection();

		sdkLogic = new SdkLogic();
		currentUser = new User();

	}

	// run method that adds the actionlistener classes for each of the JPanels
	// and effectively runs the frontend

	public void run(){

		snakeScreen.getLogin().addActionListener(new LoginActionListener());
		snakeScreen.getMenu().addActionListener(new MenuActionListener());
		snakeScreen.getNewGame().addActionListener(new NewGameActionListener());
		snakeScreen.getHighscores().addActionListener(new HighscoresActionListener());
		snakeScreen.getAbout().addActionListener(new AboutActionListener());
		snakeScreen.music();
		snakeScreen.show(snakeScreen.Login);

		//serverConnection.get("api/");
		//serverConnection.get("api/games/open/");
		//System.out.println(sdkLogic.getHighscores());
		//System.out.println(sdkLogic.openGames());

	}

	// trolling/Rick Roll method - because i can

	// actionlistener for the Login JPanel, which ensures that
	// the user can login and accordingly be rejected if inputs are incorrect

	public class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

					if (e.getSource() == snakeScreen.getLogin().getLoginBtn()) {
						try{
						currentUser.setUsername(snakeScreen.getLogin().getUsernameField());
						currentUser.setPassword(snakeScreen.getLogin().getPasswordField());
						String message = sdkLogic.login(currentUser);
						if (message.equals("Login successful")) {
							users = sdkLogic.getUsers();
							for (User usr: users){

								if(usr.getUsername().equals(snakeScreen.getLogin().getUsernameField())){
									currentUser = usr;
										System.out.println(currentUser.getUsername());
									System.out.println(currentUser.getId());
								}
							}

							snakeScreen.getMenu().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getNewGame().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getHighscores().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getAbout().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getLogin().clearFields();
							games = sdkLogic.openGames();
							snakeScreen.getNewGame().setAvailableGamesTbl(games);
							scores = sdkLogic.getHighscores();
							snakeScreen.getHighscores().setHighscoresTbl(scores);
							snakeScreen.getNewGame().getMoveBtn().setVisible(false);
							snakeScreen.getNewGame().getJoinGameRdbtn().setSelected(true);
							gamesByUser = sdkLogic.getGamesById(currentUser.getId());
							snakeScreen.getNewGame().setGamesInUsersGames(gamesByUser);
							snakeScreen.show(snakeScreen.Menu);
						} else
							JOptionPane.showMessageDialog(snakeScreen, "An error has occurred, please retype" +
									"\n username and/or password.", "Error", JOptionPane.ERROR_MESSAGE);

					}catch (NullPointerException n){
							n.printStackTrace();
						}

				}
		}
	}

	// actionlistener for the menu JPanel ensuring the JButtons work
	// accordingly

	public class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == snakeScreen.getMenu().getNewGameBtn()){
				snakeScreen.show(snakeScreen.NewGame);
			}else if(e.getSource() == snakeScreen.getMenu().getHighscoresBtn()){
				snakeScreen.show(snakeScreen.Highscores);
			}else if(e.getSource() == snakeScreen.getMenu().getAboutBtn()){
				snakeScreen.show(snakeScreen.About);
			}else if(e.getSource() == snakeScreen.getMenu().getLogOutBtn()){
				snakeScreen.show(snakeScreen.Login);
			}
		}
	}

	// acitonlistener for the newgame JPanel that enables the user
	// to create a game, join a game, and see the available/open games
	// on the server

	public class NewGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == snakeScreen.getNewGame().getCreateGameRdbtn()){

				snakeScreen.getNewGame().getJoinGameRdbtn().setSelected(false);
				snakeScreen.getNewGame().getCreateGameRdbtn().setSelected(true);
				snakeScreen.getNewGame().getMoveBtn().setVisible(true);
				snakeScreen.getNewGame().getScrollPane().setVisible(false);
			}else if(e.getSource() == snakeScreen.getNewGame().getJoinGameRdbtn()){

				snakeScreen.getNewGame().getCreateGameRdbtn().setSelected(false);
				snakeScreen.getNewGame().getJoinGameRdbtn().setSelected(true);
				snakeScreen.getNewGame().getMoveBtn().setVisible(false);
				snakeScreen.getNewGame().getScrollPane().setVisible(true);
			}else if(e.getSource() == snakeScreen.getNewGame().getMoveBtn()){

				Game createGame = new Game();
				String name = JOptionPane.showInputDialog(snakeScreen, "Please enter the name of the game: ",
						"Game name", JOptionPane.OK_CANCEL_OPTION);
				if(name.equals(JOptionPane.CANCEL_OPTION)){
					sdkLogic.deleteGame(createGame.getGameId());
				}
				createGame.setName(name);
				createGame.setMapSize(30);
				Gamer host = new Gamer();
				host.setId(currentUser.getId());
				host.setControls(snakeScreen.getNewGame().getMoveField());
				createGame.setHost(host);
				String message = sdkLogic.createGame(createGame);
				System.out.println(message);
				snakeScreen.getNewGame().setMoveField("");

			}else if(e.getSource() == snakeScreen.getNewGame().getRunGameBtn()){
				try{
				Game joinGame = new Game();
				String gameId = snakeScreen.getNewGame().getAvailableGamesTbl().
						getValueAt(snakeScreen.getNewGame().getAvailableGamesTbl().getSelectedRow(), 0).toString();
				int id = Integer.parseInt(gameId);

				joinGame.setGameId(id);
				joinGame.getGameId();
				Gamer opponent = new Gamer();
				opponent.setId(currentUser.getId());
				opponent.setControls(snakeScreen.getNewGame().getMoveField());
				joinGame.setOpponent(opponent);
				if(joinGame.getOpponent().getId() != joinGame.getHost().getId()){

					String jGame = sdkLogic.joinGame(joinGame);
					System.out.println(jGame);

					String sGame = sdkLogic.startGame(joinGame);
					System.out.println(sGame);

					String winner;

					for (User usr: users){
						try{
							if (usr.getId() == Integer.parseInt(sGame)){
								winner = usr.getUsername();
								JOptionPane.showMessageDialog(snakeScreen, jGame + " - the winner was: " + winner);
							}
						/*else{
							JOptionPane.showMessageDialog(snakeScreen, "The game was a draw.");
						}*/

						else {
					JOptionPane.showMessageDialog(snakeScreen, "Error", "This game does not allow schizophrenia - " +
							"\nYou Are not allowed to be your own opponent", JOptionPane.CANCEL_OPTION);
				} }catch(NumberFormatException n){
						n.printStackTrace();
					}
				}
				}
				}catch (NullPointerException n){
					//JOptionPane.showMessageDialog(snakeScreen, "This game does not allow schizophrenia -" +
					//		"\nYou are not allowed to be your own opponent", "ERROR", JOptionPane.CANCEL_OPTION);
				}


				/*
				*HUSK AT TJEKKE OM HOST OG OPPONENT ER SAMME PERSON.
				*/
			}else if(e.getSource() == snakeScreen.getNewGame().getDeleteGameBtn()){
				Game deleteGame = new Game();
				for (Game game: gamesByUser){
						if(game.getName().equals(snakeScreen.getNewGame().getSelectedGame())){
							deleteGame = game;
						}
					}

					String message = sdkLogic.deleteGame(deleteGame.getGameId());
					System.out.println(message);
					if(message.equals("Game was deleted")){
						snakeScreen.getNewGame().removeGame();
					}
			}else if(e.getSource() == snakeScreen.getNewGame().getMenuBtn()){
				snakeScreen.getNewGame().setMoveField("");
				snakeScreen.show(snakeScreen.Menu);
			}else if(e.getSource() == snakeScreen.getNewGame().getLogOutBtn()){
				snakeScreen.getNewGame().getMoveBtn().setVisible(false);
				snakeScreen.getNewGame().getCreateGameRdbtn().setSelected(false);
				snakeScreen.getNewGame().getJoinGameRdbtn().setSelected(true);
				snakeScreen.getNewGame().setMoveField("");
				snakeScreen.show(snakeScreen.Login);
			}

		}
	}

	// actionlistener to the highscores JPanel that shows the highscores
	// on the server

	public class HighscoresActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			//snakeScreen.getHighscores().getHighscoresTbl().setModel(sdkLogic.getHighscores());
			if(e.getSource() == snakeScreen.getHighscores().getLogOutBtn()){
				snakeScreen.show(SnakeScreen.Login);
			}else if(e.getSource() == snakeScreen.getHighscores().getMenuBtn()){
				snakeScreen.show(snakeScreen.Menu);
			}
		}
	}

	// actionlistener the the about JPanel that solidifies the RickRoll method
	// and furthermore delivers general information to the user

	public class AboutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == snakeScreen.getAbout().getMoreInfoBtn()){
				snakeScreen.music();
			}else if(e.getSource() == snakeScreen.getAbout().getLogOutBtn()){
				snakeScreen.show(snakeScreen.Login);
			}else if(e.getSource() == snakeScreen.getAbout().getMenuBtn()){
				snakeScreen.show(snakeScreen.Menu);
			}
		}
	}
}
