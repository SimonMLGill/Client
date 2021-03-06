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

	/**
	 * logic/controller class that connects the backend with the frontend
	 * creating variables of the screen-, backendlogic-, connector to server- and user class
	 */

	private SnakeScreen snakeScreen;
	private SdkLogic sdkLogic;
	private ServerConnection serverConnection;
	private User currentUser;
	private Game game;
	private ArrayList<Game> games;
	private ArrayList<Game> gamesByUser;
	private ArrayList<User > users;
	private ArrayList<Score> scores;

	/**
	 * creating objects of the aforementioned classes
	 */

	public Logic(){
		snakeScreen = new SnakeScreen();
		snakeScreen.setVisible(true);

		serverConnection = new ServerConnection();

		sdkLogic = new SdkLogic();
		currentUser = new User();

	}

	/**
	 *  run method that adds the actionlistener classes for each of the JPanels
	 * and effectively runs the frontend
	 */

	public void run(){

		snakeScreen.getLogin().addActionListener(new LoginActionListener());
		snakeScreen.getMenu().addActionListener(new MenuActionListener());
		snakeScreen.getNewGame().addActionListener(new NewGameActionListener());
		snakeScreen.getHighscores().addActionListener(new HighscoresActionListener());
		snakeScreen.getAbout().addActionListener(new AboutActionListener());
		snakeScreen.show(snakeScreen.Login);

	}

	/**
	 *  trolling/Rick Roll method - because i can
	 *  plays music via the url of the file to be played and loops the music indefinitely
	 *  whereafter it sets the imageicon of the JOptionpane to be a gif via a url
	 *  and opens the JOptionpane, and stops the music when clicking off the JOptionpane
	 */
	public void RickRollAndMusic() {
		try {


			URL url = this.getClass().getClassLoader().getResource("client/Audio/rickastley.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

			Clip clip = AudioSystem.getClip();

			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

			ImageIcon icon = new ImageIcon(this.getClass().getResource("200.gif"));
			Object obj = JOptionPane.showOptionDialog(snakeScreen, "", "You Just Got RickRoll'd M8 - Get Rekt", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, icon, null, null);
			if (obj == JOptionPane.YES_OPTION){
				clip.stop();
			} else if(obj == JOptionPane.NO_OPTION){
				clip.stop();
			} else if(obj == JOptionPane.CLOSED_OPTION){
				clip.stop();
			}
		} catch (UnsupportedAudioFileException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (LineUnavailableException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * Actionlistener for the Login JPanel, which ensures that
	 * the user can login and accordingly be rejected if inputs are incorrect
	 */

	public class LoginActionListener implements ActionListener {
		/**
		 *
		 * @param e the ActionEvent that triggers what actions to be performed
		 */
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

	/**
	 * actionlistener for the menu JPanel ensuring the JButtons work
	 * accordingly
	 */
	public class MenuActionListener implements ActionListener {
		/**
		 *
		 * @param e the ActionEvent that triggers what actions to be performed
		 */
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

	/**
	 * acitonlistener for the newgame JPanel that enables the user
	 * to create a game, join a game, start a game, delete a game, and see the available/open games
	 * on the server
	 */

	public class NewGameActionListener implements ActionListener {
		/**
		 *
		 * @param e the ActionEvent that triggers what actions to be performed
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == snakeScreen.getNewGame().getCreateGameRdbtn()){

				snakeScreen.getNewGame().getJoinGameRdbtn().setSelected(false);
				snakeScreen.getNewGame().getCreateGameRdbtn().setSelected(true);
				snakeScreen.getNewGame().getMoveBtn().setVisible(true);
				snakeScreen.getNewGame().getScrollPane().setVisible(false);
				snakeScreen.getNewGame().getRunGameBtn().setVisible(false);
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

					Game joinGame = new Game();
					String gameId = snakeScreen.getNewGame().getAvailableGamesTbl().
							getValueAt(snakeScreen.getNewGame().getAvailableGamesTbl().getSelectedRow(), 0).toString();
					int id = Integer.parseInt(gameId);

					joinGame.setGameId(id);
					joinGame.getGameId();
				for(Game g: games){
					if(g.getGameId() == joinGame.getGameId()){
						joinGame = g;
					}

				}
					Gamer opponent = new Gamer();
					opponent.setId(currentUser.getId());
					opponent.setControls(snakeScreen.getNewGame().getMoveField());
					joinGame.setOpponent(opponent);

				System.out.println(joinGame.getHost().getId());
				System.out.println(joinGame.getOpponent().getId());
				if (currentUser.getId() != joinGame.getHost().getId()) {
					String jGame = sdkLogic.joinGame(joinGame);
					System.out.println(jGame);

					String sGame = sdkLogic.startGame(joinGame);
					System.out.println(sGame);

						String winner;

						for (User usr : users) {
							try {
								if (usr.getId() == Integer.parseInt(sGame)) {
									winner = usr.getUsername();
									JOptionPane.showMessageDialog(snakeScreen, jGame + " - the winner was: " + winner);
								}

							} catch (NumberFormatException n) {
								n.printStackTrace();

							}
						}
					} else {
						JOptionPane.showMessageDialog(snakeScreen, "This game does not allow schizophrenia - " +
								"\nYou Are not allowed to be your own opponent", "Error" , JOptionPane.CANCEL_OPTION);
					}
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

	/**
	 * actionlistener to the highscores JPanel that shows the highscores
	 * on the server
	 */

	public class HighscoresActionListener implements ActionListener {
		/**
		 *
		 * @param e the ActionEvent that triggers what actions to be performed
		 */
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

	/**
	 *  actionlistener the the about JPanel that solidifies the RickRoll method
	 * and furthermore delivers general information to the user
	 */

	public class AboutActionListener implements ActionListener {
		/**
		 *
		 * @param e the ActionEvent that triggers what actions to be performed
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == snakeScreen.getAbout().getMoreInfoBtn()){
				RickRollAndMusic();
			}else if (e.getSource() == snakeScreen.getAbout().getCreateGameBtn()){
				JOptionPane.showMessageDialog(snakeScreen, "The game works as follows: \nYou create a game by tabbing the " +
						"*Create Game* button,\n" +	"inserting your controls in the textfield via the W-A-S-D keys and click the\n " +
						"*Commit* button, set the game name in the popup, and the game is created", "How to create a game", JOptionPane.OK_OPTION);
			}else if (e.getSource() == snakeScreen.getAbout().getJoinGameBtn()){
				JOptionPane.showMessageDialog(snakeScreen, "You tab the *Join Game* button, and choose a game from the table.\n" +
						"You then insert your controls in the textfield via the W-A-S-D keys\n" + "and then you click the *Run Game* " +
						"button.\n" + "If the desired game cannot be found, try logging off\n" +"and then on again.",
						"How to join and run a game", JOptionPane.OK_OPTION);
			}else if (e.getSource() == snakeScreen.getAbout().getDeleteGameBtn()){
				JOptionPane.showMessageDialog(snakeScreen, "You select a game from the dropdown, and click\n" +
						"the *Delete* button", "How to delete a game", JOptionPane.OK_OPTION);
			}else if(e.getSource() == snakeScreen.getAbout().getLogOutBtn()){
				snakeScreen.show(snakeScreen.Login);
			}else if(e.getSource() == snakeScreen.getAbout().getMenuBtn()){
				snakeScreen.show(snakeScreen.Menu);
			}
		}
	}
}
