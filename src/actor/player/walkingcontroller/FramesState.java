package actor.player.walkingcontroller;

import javafx.scene.image.Image;

/**
 * Defines an interface used for the frames states of a frog, to allow for the State design pattern.
 * @author psyjpf
 */
public interface FramesState {
	 public abstract Image[] getFrames();
}
