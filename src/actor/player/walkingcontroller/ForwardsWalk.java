package actor.player.walkingcontroller;

import actor.player.Player;
import javafx.scene.image.Image;

/**
 * Implements the logic for a frog walking forwards
 * @author psyjpf
 */
public class ForwardsWalk extends WalkingState {		
	
	public ForwardsWalk(WalkingController base) {
		super(base);
	}

	public Image[] getFrames() {
		base.player.move(0, -WalkingController.yDisplacment);
		return new Image[] {
				 new Image(Player.class.getResource("froggerUp.png").toString(), base.player.getSize(), base.player.getSize(), true, true),
				 new Image(Player.class.getResource("froggerUpJump.png").toString(), base.player.getSize(), base.player.getSize(), true, true)			
		};  
	}		
}