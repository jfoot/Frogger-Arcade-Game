package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;

import application.scoreboard.ScoreBoardModel;

/**
 * Unit tests for the ScoreBoard Model.
 * @author Jonathan Foot
 */
class ScoreBoardModelTest {

	/**
	 * Tests the scoreboard can read from a JSON file correctly and be constructed 
	 */
	@Test
	public void testSortedScoreBoardModel() {
		ScoreBoardModel temp = new ScoreBoardModel("test/resources/sorted");
		assertEquals(275, temp.getScores().get(0).Score);
		assertEquals("Jonathan", temp.getScores().get(0).Name);
	}
	
	/**
	 * Checks the score board can read from a JSON file containing unsorted scores
	 * and sort these scores in ascending order.
	 * @throws IOException
	 */
	@Test
	public void testUnsortedScoreBoardModel() throws IOException {
		Files.copy(new File("./test/resources/unsorted.json").toPath(), new File("./test/resources/newUnsorted.json").toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		ScoreBoardModel temp = new ScoreBoardModel("test/resources/newUnsorted");
		assertEquals(275, temp.getScores().get(0).Score);
		assertEquals("Jonathan", temp.getScores().get(0).Name);
	}
	
	/**
	 * Checks that you can can add a new high score to the file.
	 * @throws IOException
	 */
	@Test
	public void testAddNewScore() throws IOException {
		Files.copy(new File("./test/resources/sorted.json").toPath(), new File("./test/resources/newSorted.json").toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		ScoreBoardModel temp = new ScoreBoardModel("test/resources/newSorted");
		int orginalLen = temp.getScores().size();
		temp.addNewScore("Best Player", 300);
		assertEquals(orginalLen+1, temp.getScores().size());
		assertEquals(300, temp.getScores().get(0).Score);
		assertEquals("Best Player", temp.getScores().get(0).Name);
	}

	/**
	 * Checks that you can can add a new high score to the file AND that it is placed in 
	 * the correct position of the high score list - In this case the very end.
	 * @throws IOException
	 */
	@Test
	public void testAddNewScoreBottom() throws IOException {
		Files.copy(new File("./test/resources/sorted.json").toPath(), new File("./test/resources/newSorted.json").toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		ScoreBoardModel temp = new ScoreBoardModel("test/resources/newSorted");
		int orginalLen = temp.getScores().size();
		temp.addNewScore("Worst Player", 0);
		assertEquals(orginalLen+1, temp.getScores().size());
		assertEquals(0, temp.getScores().get(orginalLen).Score);
		assertEquals("Worst Player", temp.getScores().get(orginalLen).Name);
	}
}
