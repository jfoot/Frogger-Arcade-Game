package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import application.scoreboard.Score;

/**
 * Unit testing for the Score Class
 * @author Jonathan Foot
 */
class ScoreTest {

	/**
	 * Tests to ensure that the Score object is outputed correctly to the user.
	 */
	@Test
	public void toStringTest() {
		Score temp = new Score("Jonathan", 50);
		assertEquals("Jonathan (50)", temp.toString());
	}

}
