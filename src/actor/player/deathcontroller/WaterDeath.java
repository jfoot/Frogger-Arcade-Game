package actor.player.deathcontroller;

import javafx.scene.image.Image;

/**
 * Implements the frames needed for the water death animation.
 * This is it's own class to enforce the state design pattern.
 * @author psyjpf
 */
public class WaterDeath implements DeathState{
	
	public Image[] getFrames(int size) {
		return new Image[] {
				new Image(DeathController.class.getResource("waterdeath1.png").toString(), size, size, true, true),
				new Image(DeathController.class.getResource("waterdeath2.png").toString(), size, size, true, true),
				new Image(DeathController.class.getResource("waterdeath3.png").toString(), size, size, true, true),
				new Image(DeathController.class.getResource("waterdeath4.png").toString(), size, size, true, true)
		};  
	}
	
}