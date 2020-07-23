package actor.player.deathcontroller;

import actor.Act;
import actor.player.Player;
import actor.player.walkingcontroller.WalkingController;
import javafx.scene.image.Image;

/**
 * Used to control if the player is dying or not and if so what the player should be doing.
 * This implements the state design pattern.
 * @author psyjpf
 */
public class DeathController implements Act {
	
	private Player player;
	private DeathState state;
	private int animationFrame = 0; 

	/**
	 * Constructs a new death controller and links to the player via aggregation.
	 * @param base a reference to the player object for which the controller acts.
	 */
	public DeathController(Player base) {
		player = base;
	}
	
	/**
	 * Defines what the player should be doing each frame, if the state is null then it is not dying 
	 * Else it is dying in which case get the animation for that specific death.
	 * Once that animation is completed then reset the frog back to the start position and reduce score.
	 */
	@Override
	public void act(long now) {
		if (state != null) {
			incrementAnimation(now);
			
			if (animationFrame < state.getFrames(player.getSize()).length) {
				player.setImage(state.getFrames(player.getSize())[animationFrame]);
			}
			else {
				player.setX(300);
				player.setY(679.8 + WalkingController.yDisplacment);
				player.setImage(new Image(Player.class.getResource("froggerUp.png").toString(), player.getSize(), player.getSize(), true, true));
				
				if (player.getPoints()>50) {
					player.setPoints(player.getPoints() - 50);
				}
				player.decrementLife();
				clearState();
			}
		}			
	}
	
	/**
	 * Used to ensure the frame of the animation is displayed for more than 1 frame/tick.
	 * @param frame The current frame the program animation timer is on.
	 */
	public void incrementAnimation(long frame) {
		if (frame % 11 == 0) 
			animationFrame++;
	}
	
	
	/**
	 * Lets you set the death state for the object.
	 * @param obj the Death state.
	 */
	public void setState(DeathState obj) {			
		if(obj == null)
			clearState();
		state = obj;
	}
	
	/**
	 * @return the current Death State of the object
	 */
	public DeathState getState() {
		return state;
	}
	
	/**
	 * Sets the death state back to normal/ alive.
	 */
	private void clearState() {
		state = null;
		animationFrame = 0;
	}

}