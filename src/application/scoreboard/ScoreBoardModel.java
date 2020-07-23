package application.scoreboard;


import java.io.File;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Jonathan Foot
 */
public class ScoreBoardModel {

	private String currentLevel;
	private ObservableList<Score> scores;
	
	/**
	 * The scores are saved into an Observable list which allows us to bind the data values to the display and use the observer patteren.
	 * Every time a new score is added this will be automatically updated on the GUI front end.
	 * @return A list of all the players scores for the score board.
	 */
	public ObservableList<Score>  getScores(){
		return scores;
	}

	/**
	 * Constructs a new score board, this will get the data from a file in JSON format and then convert it into to objects.
	 * Once it has an observable list of objects it will sort the objects based upon their score property. 
	 * @param currentLevel The name of the level to get data for, this will be the levels class name.
	 */
	public ScoreBoardModel(String currentLevel) {
		this.currentLevel = currentLevel;
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("./"+currentLevel+".json");
		try {
			file.createNewFile();
			scores = FXCollections.observableList(new ArrayList<Score>(Arrays.asList(objectMapper.readValue(file, Score[].class))));
			Collections.sort(scores, ((o1, o2) -> o1.Score == o2.Score ? 0 :  o1.Score < o2.Score ? 1 : -1));		
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Highscores yet!");
			alert.setHeaderText("No one has currently set any high score for this level.");
			alert.setContentText("Why not give it a play and set some targets to beat!");
			alert.show();
			scores = FXCollections.observableList(new ArrayList<Score>());
		}
	}
	
	/**
	 * Lets you add a new score to the score board, this will then re-order the list based upon score to ensure it is 
	 * Positioned it the correct place. Finally it will save back to the JSON file for future use.
	 * @param name what name the player wants to add to the score board.
	 * @param score What score the player achieved
	 */
	public void addNewScore(String name, int score) {
	
		try {
			scores.add(new Score(name, score));
			Collections.sort(scores, ((o1, o2) -> o1.Score == o2.Score ? 0 :  o1.Score < o2.Score ? 1 : -1));
			File file = new File("./"+currentLevel+".json");
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(file, scores);
		}  catch (Exception e) {
			System.out.println("Writter Failed");
			e.printStackTrace();
		}
	}
	
}
