package level;

import actor.BackgroundImage;
import actor.End;
import application.MyStage;

/**
 * An abstract class which defines the methods and logic needed to created your own levels for the game.
 * @author psyjpf
 *
 */
public abstract class GenerateLevel {
	private final int WaterLevel;
	private final int EndGoals;
	protected final MyStage Background;
	private Boolean PlayMusic;
	
	/**
	 * Constructs a new level
	 * @param waterLevel The height of the water in the level
	 * @param noOfGoals The number of end goals in the level
	 * @param base The parent container to draw onto. 
	 * @param BackgroundImage The background image for the level.
	 * @param playMusic If you are playing the music or not.
	 */
	protected GenerateLevel(int waterLevel, int noOfGoals, MyStage base, String BackgroundImage, Boolean playMusic) {
		WaterLevel = waterLevel;
		EndGoals = noOfGoals;
		Background = base;
		PlayMusic = playMusic;
		Background.add(new BackgroundImage(BackgroundImage));
		generateEnd(EndGoals);
	}
	
	/**
	 * @return The current height of the water level.
	 */
	public int getWaterLevel() {
		return WaterLevel;
	}
	/**
	 * @return The number of goals on the level.
	 */
	public int getEndGoals() {
		return EndGoals;
	}
	
	/**
	 * @return has the player reached all of the goals, ie are all goals filled with a frog.
	 */
	public boolean isEndGoalComplete() {
		for (final End end : Background.getObjects(End.class)) {
			if (!end.isActivated()) 
				return false;
		}
		return true;
	}
	
	protected abstract void generateRow1();
	protected abstract void generateRow2();
	protected abstract void generateRow3();
	protected abstract void generateRow4();
	protected abstract void generateRow5();
	protected abstract void generateRow6();
	protected abstract void generateRow7();
	protected abstract void generateRow8();
	protected abstract void generateRow9();
	
	/**
	 * Generates the end goals for the level and positions them automatically.
	 * @param count The number of end goals you want in the level.
	 */
	private void generateEnd(int count) {
		for(int i = 0; i < count; i++) {
			Background.add(new End((BackgroundImage.width / (count * 2)) + (BackgroundImage.width / count) * i - 35, 96));
		}	
	}
	
	/**
	 * Generates all rows in the game 1-9
	 */
	protected final void generateAllRows() {
		generateRow1();
		generateRow2();
		generateRow3();
		generateRow4();
		generateRow5();
		generateRow6();
		generateRow7();
		generateRow8();
		generateRow9();
	}
	
	/**
	 * @return Returns if music should be playing or not in the level.
	 */
	public Boolean getPlayMusic() {
		return PlayMusic;
	}
}
