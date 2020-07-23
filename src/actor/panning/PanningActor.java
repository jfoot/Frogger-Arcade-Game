package actor.panning;

import actor.Act;
import actor.Actor;
import actor.BackgroundImage;
import javafx.scene.image.Image;

/**
 * Defines any object which only needs to move across the screen in a loop in the X direction,
 *  this includes, cars, trucks and logs.
 * @author Jonathan Foot
 */
public class PanningActor extends Actor implements Act {
	
	/**
	 * Speed in which the actor will pan
	 */
	private double speed;
	/**
	 * If the player collides with this will it kill the player. Eg- logs - false, car - true
	 */
	private boolean isSafe = true;

	/**
	 * Constructs a new panning actor
	 * @param imageLink Defines what sprite image to use
	 * @param xpos states the X position of the sprite
	 * @param ypos states the Y position of the sprite
	 * @param size states the size of the sprite
	 * @param speed states the speed at which the sprite moves
	 * @param isSafe states if you can touch the sprite or not with out dying
	 */
	public PanningActor(String imageLink, int xpos, int ypos, int size,  double speed, boolean isSafe) {
		this(xpos,ypos, speed);
		setIsSafe(isSafe);
		setImage(new Image(imageLink, size,size, true, true));
	}
	
	/**
	 * Constructs a new panning actor
	 * @param xpos states the X position of the sprite
	 * @param ypos states the Y position of the sprite
	 * @param speed states the speed at which the sprite moves
	 */
	protected PanningActor(int xpos, int ypos, double speed) {
		setX(xpos);
		setY(ypos);
		setSpeed(speed);
	}
	
	
	/**
	 * Defines what the actor should do each frame, this is abstracted out, when an actor moves off screen
	 * then move the actor back to the opposite side of the screen.
	 */
	@Override
	public void act(long now) {
		move(speed , 0);
	
		if (getX()> BackgroundImage.width && speed>0)
			setX(-getWidth());
		if (getX()< -getWidth() && speed<0)
			setX(BackgroundImage.width );
	}
	/**
	 * This is used for when the player is on top of a log or turle so that the player can move with the panning obstacle.
	 * @return the speed of the current actor
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * @param speed sets the speed of the object.
	 */
	protected void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * @return If the Actor is safe or not too touch
	 */
	public boolean isSafe() {
		return isSafe;
	}

	/**
	 * @param isSafe Is this a safe object to touch or not.
	 */
	protected void setIsSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
}
