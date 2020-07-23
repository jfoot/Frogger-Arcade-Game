package application.scoreboard;

/**
 * Used to store a users score record in the score board. These objects will later be saved into JSON file format and parsed.
 * @author Jonathan Foot
 */
public class Score {
	public String Name;
	public int Score;
	
	public Score() {		
	}
	
	public Score(String Name, int Score) {
		this.Name = Name;
		this.Score = Score;
	}
	
	/**
	 * Used to display the object to the user on the score board.
	 */
	@Override
	public String toString() {
		return Name + " (" + Score+ ")";
	}
}
