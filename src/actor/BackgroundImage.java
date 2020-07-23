package actor;

import javafx.scene.image.Image;
/**
 * Defines the creation of the background object.
 * @author Jonathan Foot
 */
public class BackgroundImage extends Actor{
	
	public static final int width = 600;
	public static final int height = 800;
	
	/**
	 * Creates the background for the game.
	 * @param imageLink The location of the image file
	 */
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, width, height, false, true));
	}

}
