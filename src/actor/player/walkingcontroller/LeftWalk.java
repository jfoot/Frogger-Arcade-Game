package actor.player.walkingcontroller;

import actor.player.Player;
import javafx.scene.image.Image;

/**
 * Implements the logic for a frog walking left
 * @author psyjpf
 */
public class LeftWalk extends WalkingState {

	public LeftWalk(WalkingController base) {
		super(base);
	}
	
	public Image[] getFrames() {
		base.player.move(-WalkingController.xDisplacment, 0);
		return new Image[] {
				 new Image(Player.class.getResource("froggerLeft.png").toString(),base.player.getSize(), base.player.getSize(), true, true),
				 new Image(Player.class.getResource("froggerLeftJump.png").toString(), base.player.getSize(), base.player.getSize(), true, true)		
		};  
	}		

}
