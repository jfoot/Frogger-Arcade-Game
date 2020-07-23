package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import level.*;

/**
 * Defines the controller for the StartScreen.FXML document
 * @author psyjpf
 */
public class StartScreenController {
	
	@FXML
	private Button musicButton;	
	@FXML
	private Text LivesText;
	@FXML
	private Slider LivesSlider;
	
	private Boolean PlayMusic = true;
	
	/**
	 * Defines the logic for when the user toggles on and off the background music.
	 * @throws IOException
	 */
	@FXML
    private void musicClick() throws IOException {
		PlayMusic= !PlayMusic;
		musicButton.setText(PlayMusic ? "Turn Off Music" : "Turn On Music");
	}
	
	/**
	 * Displays the games instructions
	 * @throws IOException
	 */
	@FXML
    private void helpClick() throws IOException {
		MyStage.getInstanceSingelton().removeAll();
		MyStage.getInstanceSingelton().change("InfoScreen");
	}
	
	/**
	 * Launches level 1.
	 * @throws IOException
	 */
	@FXML
    private void level1() throws IOException {
		MyStage.getInstanceSingelton().removeAll();
		MyStage.getInstanceSingelton().change(new Level1(MyStage.getInstanceSingelton(), PlayMusic), getLivesCounts());
    }
	/**
	 * Displays the leader board for level 1
	 */
	@FXML 
	private void level1Score() {
		MyStage.getInstanceSingelton().scoreBoardShow(Level1.class.getName(),null);
	}
	
	/**
	 * Launches level 2.
	 * @throws IOException
	 */
	@FXML
    private void level2() throws IOException {
		MyStage.getInstanceSingelton().removeAll();
		MyStage.getInstanceSingelton().change(new Level2(MyStage.getInstanceSingelton(), PlayMusic), getLivesCounts());
    }
	/**
	 * Displays the leader board for level 2
	 */
	@FXML 
	private void level2Score() {
		MyStage.getInstanceSingelton().scoreBoardShow(Level2.class.getName(),null);
	}
	/**
	 * Launches level 3.
	 * @throws IOException
	 */
	@FXML
    private void level3() throws IOException {
		MyStage.getInstanceSingelton().removeAll();
		MyStage.getInstanceSingelton().change(new Level3(MyStage.getInstanceSingelton(), PlayMusic), getLivesCounts());
    }
	/**
	 * Displays the leader board for level 3
	 */
	@FXML 
	private void level3Score() {
		MyStage.getInstanceSingelton().scoreBoardShow(Level3.class.getName(), null);
	}
	
	/**
	 * Called upon whenever the user moves the slider which controlls the number of lives they have.
	 * @throws IOException
	 */
	@FXML
    private void dragStarted() throws IOException {
		if((int)LivesSlider.getValue() == 16) {
			LivesText.setText("Infinte Number of Lives");
		}else {
			LivesText.setText("Number of Lives: " + (int)LivesSlider.getValue());
		}
	}
	/**
	 * @return Returns the number of lives the player should have in the level to the Level object, null = infinite
	 */
	private Integer getLivesCounts() {		
		return (int)LivesSlider.getValue() == (int) LivesSlider.getMax() ? null : (int)LivesSlider.getValue();
	}
}
