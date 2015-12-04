package client.gui;

import sun.audio.AudioPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


public class SnakeScreen extends JFrame {

	// screen class - effectively my JFrame
	// variables/JPanels to the JFrame

	public static final String Login = "login";
	public static final String Menu = "menu";
	public static final String Highscores = "highscores";
	public static final String NewGame = "newGame";
	public static final String About = "about";
	private JPanel contentPane;
	public client.gui.Login login;
	public client.gui.Menu menu;
	public client.gui.Highscores highscores;
	public client.gui.NewGame newGame;
	public client.gui.About about;
	private CardLayout c;

	/**
	 * Create the frame.
	 */

	// adding the JPanels to the JFrame

	public SnakeScreen() {
		setTitle("No Snake Eyes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(contentPane);
		
		login = new Login();
		contentPane.add(login, Login);
		
		menu = new Menu();
		contentPane.add(menu, Menu);
		
		highscores = new Highscores();
		contentPane.add(highscores, Highscores);
		
		newGame = new NewGame();
		contentPane.add(newGame, NewGame);
		
		about = new About();
		contentPane.add(about, About);
		
		c = (CardLayout) getContentPane().getLayout();
	}

	// getters to my JPanels

	public client.gui.Login getLogin(){
		return login;
	}
	
	public client.gui.Menu getMenu(){
		return menu;
	}
	
	public client.gui.Highscores getHighscores(){
		return highscores;
	}
	
	public client.gui.NewGame getNewGame(){
		return newGame;
	}
	
	public client.gui.About getAbout(){
		return about;
	}

	// method that ensures that my CardLayout is in effect
	
	public void show(String card){
		c.show(this.getContentPane(), card);
	}

	/*public void RickRoll(){
		String url = "rickastley.wav";
		try {
			//InputStream rickRoll = new FileInputStream(this.getClass().getResource(url));
			//File sound = new File("rickastley_artists.wav");
			URL ur = this.getClass().getClassLoader().getResource("music/rickastley.wav");
			//AudioInputStream RR = new AudioSystem.getAudioInputStream(sound);
			AudioInputStream RR = new AudioSystem.getAudioInputStream(ur);
			Clip clip = AudioSystem.getClip();
			clip.open(RR);
			clip.start();
			//AudioStream RR;
			//RR = new AudioStream(rickRoll);
			AudioPlayer.player.start(RR);

			ImageIcon icon = new ImageIcon(this.getClass().getResource("200.gif"));
			//JOptionPane.showMessageDialog(snakeScreen, "", "You Just Got RickRoll'd M8, Get Rekt", JOptionPane.OK_OPTION, icon);
			AudioPlayer.player.stop(RR);

		}catch (Exception i){
			i.printStackTrace();
		}
	}*/

	public void music()
	{
		try {

			// Open an audio input stream.
			URL url = this.getClass().getClassLoader().getResource("Audio/rickastley.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			// start()
// Loop()
			clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
		} catch (UnsupportedAudioFileException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (LineUnavailableException e) {
			//e.printStackTrace();
		}
	}

}
