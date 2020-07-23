package actor.player.deathcontroller;

import javafx.scene.image.Image;

/**
 * Implements the frames needed for the car death animation.
 * This is it's own class to enforce the state design pattern.
 * @author psyjpf
 */
public class CarDeath implements DeathState  {	
	
	public Image[] getFrames(int size) {
		return new Image[] {
				new Image(DeathController.class.getResource("cardeath1.png").toString(), size, size, true, true),
				new Image(DeathController.class.getResource("cardeath2.png").toString(), size,size, true, true),
				new Image(DeathController.class.getResource("cardeath3.png").toString(), size, size, true, true)
		};  
	}		
}
