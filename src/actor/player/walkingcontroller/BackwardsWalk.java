package actor.player.walkingcontroller;

import actor.player.Player;
import javafx.scene.image.Image;

/**
 * Implements the logic for a frog walking Backwards
 * @author psyjpf
 */
public class BackwardsWalk extends WalkingState {		
	
	public BackwardsWalk(WalkingController base) {
		super(base);
	}

	public Image[] getFrames() {
		base.player.move(0, WalkingController.yDisplacment);
		return new Image[] {
				 new Image(Player.class.getResource("froggerDown.png").toString(), base.player.getSize(), base.player.getSize(), true, true),
				 new Image(Player.class.getResource("froggerDownJump.png").toString(), base.player.getSize(), base.player.getSize(), true, true)		
		};  
	}		
}