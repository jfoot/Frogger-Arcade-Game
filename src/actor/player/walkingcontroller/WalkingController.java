package actor.player.walkingcontroller;

import actor.player.Player;
import javafx.scene.image.Image;

/**
 * Used to control the direction the player is walking in and what should be displayed.
 * @author psyjpf
 */
public class WalkingController {
	protected Player player;
	private WalkingState state = new ForwardsWalk(this);
	private int animationFrame = 0; 

	public final static double yDisplacment =  13.3333333*2;
	public final static double xDisplacment = 10.666666*2;
	
	/**
	 * Constructs the Walking state control and links to the player via aggregation.
	 * @param base a reference to the player object for which the controller acts.
	 */
	public WalkingController(Player base) {
		this.player = base;
	}
	
	/**
	 * Sets the walking state for the player
	 * @param state what walking state you want the player to have.
	 */
	public void setState(WalkingState state) {			
		if(this.state.getClass() != state.getClass() && (animationFrame%2) == 0)
			this.state = state;	

		player.setImage(this.state.getFrames()[(++animationFrame % 2)]);
	}
	
	/**
	 * Clears the walking state, so frog is neutral facing which ever way it last moved.
	 */
	public void clearState() {
		animationFrame = 0;
		player.setImage(state.getFrames()[animationFrame]);
	}

	/**
	 * Resets the frog to just look forwards, without moving the frog, used after a frog has died.
	 */
	public void resetState() {
		animationFrame = 0;
		player.setImage( new Image(Player.class.getResource("froggerUp.png").toString(), player.getSize(), player.getSize(), true, true));
	}
}
