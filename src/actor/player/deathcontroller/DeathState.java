package actor.player.deathcontroller;

import javafx.scene.image.Image;

/**
 * Defines an interface used for the death states of a frog, to allow for the State design pattern.
 * @author psyjpf
 */
public interface DeathState {
	 public abstract Image[] getFrames(int size);
}
