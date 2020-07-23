package actor;

import javafx.scene.image.Image;
/**
 * Originally called Digit, this class is used both to display the score but also the hearts to the player
 * It therefore lets use place an Actor onto the screen as an overlay with ease.
 * @author Jonathan Foot
 */
public class Overlay extends Actor{
	
	/**
	 * 	Creates a digit number for the score on the level screen.
	 * @param name The name of the image file to be displayed
	 * @param dimensions The dimensions of the number
	 * @param x The X position of the image
	 * @param y The Y position of the image.
	 */
	public Overlay(String name, int dimensions, int x, int y) {
		Image im1 = new Image(name, dimensions, dimensions, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}
	
}
