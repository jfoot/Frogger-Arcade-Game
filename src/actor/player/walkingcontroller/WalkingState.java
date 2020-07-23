package actor.player.walkingcontroller;

/**
 * Defines the logic needed for a walking state, all states need an aggregate reference to the controller.
 * @author psyjpf
 */
public abstract class WalkingState implements FramesState {
	protected WalkingController base;
		
	public WalkingState(WalkingController base) {
		this.base = base;
	}
}
