package actor;

/**
 * Created an interface for the Actors which need the Act method, as not all classes that 
 * implement Actor need Act. This prevents unneeded property inheritance. 
 * @author Jonathan Foot
 */
public interface Act {
	/**
	 * The method which implements what action the actor should do each frame.
	 * @param now The current frame the animation timer of the program is currently on.
	 */
	 public abstract void act(long now);
}
