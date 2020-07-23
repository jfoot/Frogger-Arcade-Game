package application;


import java.io.IOException;
import actor.BackgroundImage;
import application.scoreboard.ScoreBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import level.GenerateLevel;
import level.Game;


/**
 * Defines the main logic for the program and contains all the graphics. A singleton design pattern is used 
 * to ensure that you can never have more than one of these at any given point in time.
 * @author psyjpf
 */
public class MyStage extends World{
	
	private Stage primaryStage;
	private MediaPlayer mediaPlayer;
	private Scene levelScene = new Scene(this, BackgroundImage.width, BackgroundImage.height);
	private Game level;
	
	private static MyStage instance;
	
	/**
	 * Sets the private constructor for the object.
	 * @param stage Defines the parent container for this pane.
	 * @throws IOException constructing can throw an error which is contained here.
	 */
	private MyStage(Stage stage) throws IOException {
		primaryStage = stage;
		change("StartScreen");
		primaryStage.show();
	}
		
	
	/**
	 * Gets a single instance of the object or creates a new one if one does not exist.
	 * @param stage The main stage reference needed to create a MyStage object
	 * @return a single instance of My Stage using a singleton to ensure not more than one is ever created.
	 */
	public static MyStage getInstanceSingelton(Stage stage) {
		if (instance == null && stage != null) {
			try {
				instance = new MyStage(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * @return A reference to the object, ensures never more than one.
	 */
	public static MyStage getInstanceSingelton() {
		return instance;
	}
	

	/**
	 * Used for if you want to change levels to a new level in the main menu screen
	 * @param newLevel The new level for which you wish to load.
	 * @param lives The number of lives the player has on the level.
	 * @throws IOException The error message which can be generated when changing.
	 */
	public void change(GenerateLevel newLevel, Integer lives) throws IOException {
		primaryStage.setScene(levelScene);
		level = new Game(this, newLevel, lives);
	}
	
	/**
	 * Change the view to any FMXL file, for example the info screen and high score scene.
	 * @param newScene - the name of the FXML file for which the program needs to switch too/ display.
	 */
	public void change(String newScene){
		try {
			Scene tempScene;
			tempScene = new Scene(loadFXML(newScene),BackgroundImage.width, BackgroundImage.height);
			tempScene.getStylesheets().add("style.css");
			primaryStage.setScene(tempScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays the score board on screen to the player to view.
	 * @param level What score board you want to display/ for what level
	 * @param score What score the user got that they might want to enter into the scoreboard file.
	 */
	public void scoreBoardShow(String level, Integer score) {
	
		Scene tempScene;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ScoreBoard.fxml"));
	
			tempScene = new Scene(fxmlLoader.load(),BackgroundImage.width, BackgroundImage.height);
			ScoreBoardController controller = fxmlLoader.<ScoreBoardController>getController();
			controller.setModel(level, score);
			
			tempScene.getStylesheets().add("style.css");
			primaryStage.setScene(tempScene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * Used to load up a new window from a custom .FXML file.
	 * @param fxml The file name for what you want to load.
	 * @return The parsed FXml document
	 * @throws IOException the error which can be generated when loading an FXML file.
	 */
	 public Parent loadFXML(String fxml) throws IOException {
		 FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
		 return fxmlLoader.load();
	 }
	
	/**
	 * Begins the BackgroundImage music for the game.
	 */
	public void playMusic() {
		Media sound = new Media(MyStage.class.getResource("Frogger Main Song Theme (loop).mp3").toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	/**
	 * Stops the Background music for the game.
	 */
	public void stopMusic() {
		if(mediaPlayer != null)
			mediaPlayer.stop();
	}

	/**
	 * Used only for unit testing
	 * @return the level which is currently being displayed on the main stage.
	 */
	public Game getLevel() {
		return level;
	}

}
