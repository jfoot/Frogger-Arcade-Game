package actor;

import javafx.scene.image.Image;
/**
 * Defines the frog goals/ targets at the end of the level.
 * @author Jonathan Foot
 */
public class End extends Actor{
	
	private boolean activated = false;

	/**
	 * Constructor to create a new goal in a level
	 * @param x The X position for this goal state
	 * @param y The Y position for this goal state
	 */
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image(End.class.getResource("End.png").toString(), 70, 70, true, true));
	}
	
	/**
	 * Changes the frog from an empty goal state to a completed goal state.
	 */
	public void setEnd() {
		setImage(new Image(End.class.getResource("FrogEnd.png").toString(), 70, 70, true, true));
		activated = true;
	}
	
	/**
	 * @return Is the goal state filled up/ the player has already reached this goal.
	 */
	public boolean isActivated() {
		return activated;
	}
	
}
