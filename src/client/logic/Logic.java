package client.logic;

import client.gui.SnakeScreen;
import client.sdk.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		snakeScreen.show(snakeScreen.Login);

		//serverConnection.get("api/");
		//serverConnection.get("api/games/open/");
		//System.out.println(sdkLogic.getHighscores());
		//System.out.println(sdkLogic.openGames());

	}

	// trolling/Rick Roll method - because i can

	public void RickRoll(String url){
		try{
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		}catch(java.io.IOException r){
			System.out.print("error");
		}
	}

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
							snakeScreen.getMenu().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getNewGame().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getHighscores().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getAbout().setUserField(snakeScreen.getLogin().getUsernameField());
							snakeScreen.getLogin().clearFields();
							games = sdkLogic.openGames();
							snakeScreen.getNewGame().setAvailableGamesTbl(games);
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
			if(e.getSource() == snakeScreen.getNewGame().getMoveBtn()){

				Game createGame = new Game();
				String name = JOptionPane.showInputDialog(snakeScreen, "Please enter the name of the game: ",
						"Game name", JOptionPane.OK_CANCEL_OPTION);
				if(name.equals(JOptionPane.CANCEL_OPTION)){
					sdkLogic.deleteGame(createGame.getGameId());
				}
				createGame.setName(name);
				createGame.setMapSize(30);
				Gamer host = new Gamer();
				host.setUserId(currentUser.getUserId());
				host.setControls(snakeScreen.getNewGame().getMoveField());
				createGame.setHost(host);
				String message = sdkLogic.createGame(createGame);
				snakeScreen.getNewGame().setLogArea(snakeScreen.getNewGame().getMoveField());
				System.out.println(message);


				//System.out.println(games);
				//TableModelOpenGames tm = new TableModelOpenGames(games);
				/*for(Game game: games){
					tm.addGame(game);
				}*/
				//tm.setGames(games);
				//snakeScreen.getNewGame().getAvailableGamesTbl().setModel(tm);





			}else if(e.getSource() == snakeScreen.getNewGame().getShowLogRdbtn()){
				snakeScreen.getNewGame().getHideLogRdbtn().setSelected(false);
				snakeScreen.getNewGame().getShowLogRdbtn().setSelected(true);
				snakeScreen.getNewGame().getLogArea().setVisible(true);
			} else if(e.getSource() == snakeScreen.getNewGame().getHideLogRdbtn()){
				snakeScreen.getNewGame().getShowLogRdbtn().setSelected(true);
				snakeScreen.getNewGame().getHideLogRdbtn().setSelected(false);
				snakeScreen.getNewGame().getLogArea().setVisible(false);
			}else if(e.getSource() == snakeScreen.getNewGame().getMenuBtn()){
				snakeScreen.show(snakeScreen.Menu);
			}else if(e.getSource() == snakeScreen.getNewGame().getLogOutBtn()){
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
				RickRoll("https://www.youtube.com/watch?v=BROWqjuTM0g");
			}else if(e.getSource() == snakeScreen.getAbout().getLogOutBtn()){
				snakeScreen.show(snakeScreen.Login);
			}else if(e.getSource() == snakeScreen.getAbout().getMenuBtn()){
				snakeScreen.show(snakeScreen.Menu);
			}
		}
	}
}
