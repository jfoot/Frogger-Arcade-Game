package level;


import java.util.ArrayList;

import actor.Actor;
import actor.BackgroundImage;
import actor.Overlay;
import actor.player.Player;
import application.MyStage;
import level.Observer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Defines the level class, which contains all the information about a level.
 * @author psyjpf
 */
public class Game implements Observer {
	
	private GenerateLevel mapData; 
	private MyStage base;
	private Player frog;
	private ArrayList<Overlay> hearts = new ArrayList<Overlay>();

	/**
	 * Constructs a new level, adds in the player and begins the music and timers.
	 * @param base Keeps an aggregate pointer to the parent container to draw too
	 * @param LevelToMake Says what level you want to generate
	 * @param Lives The number of lives the player has in this game
	 */
	public Game(MyStage base, GenerateLevel LevelToMake, Integer Lives) {
		this.base = base;
		mapData = LevelToMake;
		frog = new Player(Player.class.getResource("froggerUp.png").toString(), Lives, this, mapData.getWaterLevel());
		base.add(frog);	
		base.add(new Overlay(Actor.class.getResource("0.png").toString(), 30, 500, 25));

		generateHeartsOverlay(Lives);
	
		if(mapData.getPlayMusic())
			base.playMusic();
		
		base.start();
	}
	
	/**
	 * Implements the observer pattern, instead of pooling and checking every frame for a change in the
	 * players score we now let the player tell us when the score has changed and in doing so if they have one
	 * the game or if they have lost it.
	 */
	@Override
	public void update() {
		updateScore();
    	hideHearts();
    	checkGameOver();
    	checkEnd();     	
	}
	
	
	/**
	 * Generates the hearts to represent number of lives.
	 * @param Lives the number of lives the player has.
	 */
	private void generateHeartsOverlay(Integer Lives) {
		if(Lives != null) {
			int heartSize = Lives < 10 ? 30 : 25;
			for(int i = 0; i <Lives; i++) {
				hearts.add(new Overlay(Actor.class.getResource("heart.png").toString(),heartSize, 10  + (i*heartSize), BackgroundImage.height - 50));
				base.add(hearts.get(i));			
			}
		}
	}
	
	/**
	 * Updates the score on the screen to the player.
	 */
	private void updateScore() {
		int n = frog.getPoints();
		int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  base.add(new Overlay(Actor.class.getResource(k + ".png").toString(), 30, 500 - shift, 25));
    		  shift+=30;
    	}
	}
	
	/**
	 * Updates the hearts on the screen to the player to see how many lives left.
	 */
	private void hideHearts() {
		if(frog.getLives() != null) {
    		for(int i = frog.getLives(); i < hearts.size(); i++) {
    			hearts.get(i).setImage(null);		
    		}
    	}
	}
	
	
	/**
	 * Generates the final score
	 * @return The points earned in the game plus the number of lives remaining * 15
	 */
	public int calculateFinalScore() {
		return frog.getPoints() + (frog.getLives() != null ? (frog.getLives()/hearts.size()) * 25 : 0);
	}
	
	
	/**
	 * Checks if the player has won the game and if so launch winning screen and return to main menu.
	 */
	public void checkEnd() {
		if(mapData.isEndGoalComplete()) {
			base.stopMusic();
			base.stop();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("You Have Won The Game!");
			alert.setHeaderText("Your High Score: "+ calculateFinalScore() +"!");
			alert.setContentText("Try and beat it!");
			alert.show();
			MyStage.getInstanceSingelton().scoreBoardShow(mapData.getClass().getName(), calculateFinalScore());
		}	
	}
	
	
	/**
	 * Checks if the player has ran out of lives and hit game over!
	 */
	public void checkGameOver() {
		if(frog.getLives() != null && frog.getLives() == 0) {
			base.stopMusic();
			base.stop();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Game Over");
			alert.setHeaderText("Game Over!");
			alert.setContentText("Better Luck Next Time!");
			alert.show();
			MyStage.getInstanceSingelton().change("StartScreen");
		}
	}


	/**
	 * @return The player on the level, used only for unit testing.
	 */
	public Player getFrog() {
		return frog;
	}
}
