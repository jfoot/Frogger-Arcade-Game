package actor.player.walkingcontroller;

import actor.player.Player;
import javafx.scene.image.Image;

/**
 * Implements the logic for a frog walking right
 * @author psyjpf
 */
public class RightWalk extends WalkingState {

	public RightWalk(WalkingController base) {
		super(base);
	}
	
	public Image[] getFrames() {
		base.player.move(WalkingController.xDisplacment, 0);
		return new Image[] {
				 new Image(Player.class.getResource("froggerRight.png").toString(), base.player.getSize(), base.player.getSize(), true, true),
				 new Image(Player.class.getResource("froggerRightJump.png").toString(), base.player.getSize(), base.player.getSize(), true, true)			
		};  
	}	
}
