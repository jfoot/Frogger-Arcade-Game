package application.scoreboard;

import java.io.IOException;
import application.MyStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Defines the controller for the ScoreBoard.FXML document
 * @author psyjpf
 */
public class ScoreBoardController {
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ListView scoreBoard;
	
	@FXML
	private Button submitButton;
	
	@FXML
	private TextField nameField;
	
	private ScoreBoardModel model;
	private Integer score;
	
	public void setModel(String currentLevel, Integer score) {
		model = new ScoreBoardModel(currentLevel);
		this.score = score;
	}
	
	/**
	 * At start up of the display call upon the model to get the data and then set it to display.
	 * If the score is null, then the user has not just completed a game (they came from main menu)
	 * If the score is non-null they have come from a game and can save their score.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
	    Platform.runLater(() -> {
	    	scoreBoard.setItems(model.getScores());	
	    	if (score == null)
	    		setDisable();
	    });
	}
	
	/**
	 * Adds a new high score to the table.
	 */
	@FXML
	private void submitClick() {
		setDisable();
		model.addNewScore(nameField.getText(), score);	
	}
	
	/**
	 * Disables the submit and text filed once a user has submitted a value or if they are simply viewing the table from main menu.
	 */
	private void setDisable() {
		nameField.setDisable(true);	
		submitButton.setDisable(true);
	}
	
	/**
	 * Return back to main menu.
	 * @throws IOException
	 */
	@FXML
    private void returnClick() throws IOException {
		MyStage.getInstanceSingelton().change("StartScreen");
	}
}
